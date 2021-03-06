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
package com.github.tahaviev.pmd;

import net.sourceforge.pmd.lang.java.ast.ASTAnnotation;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Only overridden methods rule.
 */
public final class OnlyOverriddenMethodsRule extends AbstractJavaRule {

    @Override
    public Object visit(final ASTMethodDeclaration node, final Object data) {
        if (Optional
            .of(node)
            .filter(it -> !it.isInterfaceMember())
            .filter(it -> !it.isStatic())
            .filter(it -> !it.isPrivate())
            .filter(it -> !it.isAbstract())
            .filter(
                it -> it
                    .getDeclaredAnnotations()
                    .stream()
                    .map(ASTAnnotation::getAnnotationName)
                    .noneMatch(
                        Predicate
                            .<String>isEqual("Override")
                            .or(name -> name.contains("Test"))
                    )
            )
            .isPresent()) {
            this.addViolation(data, node);
        }
        return super.visit(node, data);
    }
}
