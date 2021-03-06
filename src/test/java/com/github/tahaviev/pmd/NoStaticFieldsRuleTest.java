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

/**
 * {@link NoStaticFieldsRule} test.
 */
public final class NoStaticFieldsRuleTest extends RuleTstFixed {

    /**
     * Can find static field in annotation.
     */
    @Test
    public void findsInAnnotation() {
        this.runTest(
            new TestDescriptor(
                "@interface A {int f = 0;}",
                "can not find static field in annotation",
                1,
                new NoStaticFieldsRule()
            )
        );
    }

    /**
     * Can find static field in interface.
     */
    @Test
    public void findsInInterface() {
        this.runTest(
            new TestDescriptor(
                "interface I {F f;}",
                "can not find static field in interface",
                1,
                new NoStaticFieldsRule()
            )
        );
    }

    /**
     * Can find package private static field.
     */
    @Test
    public void findsPackagePrivate() {
        this.runTest(
            new TestDescriptor(
                "class C{static F f;}",
                "can not find package private static field",
                1,
                new NoStaticFieldsRule()
            )
        );
    }

    /**
     * Can find private static field.
     */
    @Test
    public void findsPrivate() {
        this.runTest(
            new TestDescriptor(
                "class C{private static F f;}",
                "can not find private static field",
                1,
                new NoStaticFieldsRule()
            )
        );
    }

    /**
     * Can find public static field.
     */
    @Test
    public void findsPublic() {
        this.runTest(
            new TestDescriptor(
                "class C{public static F f;}",
                "can not find public static field",
                1,
                new NoStaticFieldsRule()
            )
        );
    }

    /**
     * Can ignore non static field.
     */
    @Test
    public void ignoresNonStatic() {
        this.runTest(
            new TestDescriptor(
                "class C{F f;}",
                "can not ignore non static field",
                0,
                new NoStaticFieldsRule()
            )
        );
    }
}
