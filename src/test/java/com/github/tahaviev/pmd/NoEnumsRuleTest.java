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

import com.github.tahaviev.pmd.util.RuleTstFixed;
import net.sourceforge.pmd.testframework.TestDescriptor;
import org.junit.Test;

public final class NoEnumsRuleTest extends RuleTstFixed {

    @Test
    public void findsEnum() {
        this.runTest(
                new TestDescriptor(
                        "enum E{}",
                        "can not find enum",
                        1,
                        new NoEnumsRule()
                )
        );
    }

    @Test
    public void findsInnerEnums() {
        this.runTest(
                new TestDescriptor(
                        "interface I{enum E1{} class C {enum E2{}}}",
                        "can not find inner enums",
                        2,
                        new NoEnumsRule()
                )
        );
    }
}
