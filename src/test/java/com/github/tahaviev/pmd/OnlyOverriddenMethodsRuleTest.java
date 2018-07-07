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

public final class OnlyOverriddenMethodsRuleTest extends RuleTstFixed {

    @Test
    public void findsNonOverriddenPublic() {
        this.runTest(
                new TestDescriptor(
                        "class C{public void m(){}}",
                        "can not find non overridden public method",
                        1,
                        new OnlyOverriddenMethodsRule()
                )
        );
    }

    @Test
    public void findsNonOverriddenProtected() {
        this.runTest(
                new TestDescriptor(
                        "class C{protected void m(){}}",
                        "can not find non overridden protected method",
                        1,
                        new OnlyOverriddenMethodsRule()
                )
        );
    }

    @Test
    public void ignoresOverridden() {
        this.runTest(
                new TestDescriptor(
                        "class C{@Override public void m(){}}",
                        "can not ignore overridden public method",
                        0,
                        new OnlyOverriddenMethodsRule()
                )
        );
    }

    @Test
    public void ignoresStatic() {
        this.runTest(
                new TestDescriptor(
                        "class C{public static void m(){}}",
                        "can not ignore static method",
                        0,
                        new OnlyOverriddenMethodsRule()
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
                        new OnlyOverriddenMethodsRule()
                )
        );
    }

    @Test
    public void ignoresInterface() {
        this.runTest(
                new TestDescriptor(
                        "interface I{void m();}",
                        "can not ignore interface method",
                        0,
                        new OnlyOverriddenMethodsRule()
                )
        );
    }

    @Test
    public void ignoresAbstract() {
        this.runTest(
                new TestDescriptor(
                        "class C{public abstract void m(){}}",
                        "can not ignore abstract method",
                        0,
                        new OnlyOverriddenMethodsRule()
                )
        );
    }

    @Test
    public void ignoresTest() {
        this.runTest(
                new TestDescriptor(
                        "class C{@Test public void m(){}}",
                        "can not ignore test method",
                        0,
                        new OnlyOverriddenMethodsRule()
                )
        );
    }
}
