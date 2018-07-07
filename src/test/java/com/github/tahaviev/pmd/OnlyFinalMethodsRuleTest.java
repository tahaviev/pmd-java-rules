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

public final class OnlyFinalMethodsRuleTest extends RuleTstFixed {

    @Test
    public void findsDefault() {
        this.runTest(
                new TestDescriptor(
                        "interface I{default void m(){}}",
                        "can not find default method",
                        1,
                        new OnlyFinalMethodsRule()
                )
        );
    }

    @Test
    public void findsNonFinal() {
        this.runTest(
                new TestDescriptor(
                        "class C{public void m(){}}",
                        "can not find non final method",
                        1,
                        new OnlyFinalMethodsRule()
                )
        );
    }

    @Test
    public void ignoresAnonymousClass() {
        this.runTest(
                new TestDescriptor(
                        "class C{final void m(){new AC(){void m(){}};}}",
                        "can not ignore anonymous class",
                        0,
                        new OnlyFinalMethodsRule()
                )
        );
    }

    @Test
    public void ignoresEnum() {
        this.runTest(
                new TestDescriptor(
                        "enum E{E1;void m(){}abstract void am(){}}",
                        "can not ignore enum method",
                        0,
                        new OnlyFinalMethodsRule()
                )
        );
    }

    @Test
    public void ignoresFinal() {
        this.runTest(
                new TestDescriptor(
                        "class C{final void m(){}}",
                        "can not ignore final method",
                        0,
                        new OnlyFinalMethodsRule()
                )
        );
    }

    @Test
    public void ignoresFinalClass() {
        this.runTest(
                new TestDescriptor(
                        "final class C{public void m(){}}",
                        "can not ignore final class",
                        0,
                        new OnlyFinalMethodsRule()
                )
        );
    }

    @Test
    public void ignoresInterface() {
        this.runTest(
                new TestDescriptor(
                        "interface I{void m(){}}",
                        "can not ignore interface method",
                        0,
                        new OnlyFinalMethodsRule()
                )
        );
    }

    @Test
    public void ignoresPrivate() {
        this.runTest(
                new TestDescriptor(
                        "class C{private void m(){}}",
                        "can not ignore private method",
                        0,
                        new OnlyFinalMethodsRule()
                )
        );
    }

    @Test
    public void ignoresStatic() {
        this.runTest(
                new TestDescriptor(
                        "class C{static void m(){}}",
                        "can not ignore static method",
                        0,
                        new OnlyFinalMethodsRule()
                )
        );
    }
}
