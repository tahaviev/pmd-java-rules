/*
 * MIT License
 *
 * Copyright (c) 2018 Ramil Tahaviev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.tahaviev.pmd.util;

import lombok.RequiredArgsConstructor;
import net.sourceforge.pmd.lang.java.ast.ASTFormalParameter;
import net.sourceforge.pmd.lang.java.ast.ASTFormalParameters;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTType;
import net.sourceforge.pmd.lang.java.ast.AbstractJavaAccessNode;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public final class IsMainMethod implements Lazy<Boolean> {

    private final ASTMethodDeclaration declaration;

    @Override
    public Boolean get() {
        return Optional
                .of(this.declaration)
                .filter(AbstractJavaAccessNode::isStatic)
                .filter(ASTMethodDeclaration::isPublic)
                .filter(ASTMethodDeclaration::isVoid)
                .map(ASTMethodDeclaration::getMethodDeclarator)
                .filter(it -> it.hasImageEqualTo("main"))
                .map(it -> it.getFirstChildOfType(ASTFormalParameters.class))
                .filter(it -> it.getParameterCount() == 1)
                .map(parameters -> parameters.iterator().next())
                .filter(ASTFormalParameter::isArray)
                .map(ASTFormalParameter::getTypeNode)
                .map(ASTType::getTypeImage)
                .filter(it -> Objects.equals(it, String.class.getSimpleName()))
                .isPresent();
    }
}
