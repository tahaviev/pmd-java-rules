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
 * {@link OnlyPublicMethodsRule} test.
 */
public final class OnlyPublicMethodsRuleTest extends RuleTstFixed {

    /**
     * Can find package private method.
     */
    @Test
    public void findsPackagePrivate() {
        this.runTest(
            new TestDescriptor(
                "class C{void m(){}}",
                "can not find package private method",
                1,
                new OnlyPublicMethodsRule()
            )
        );
    }

    /**
     * Can find private method.
     */
    @Test
    public void findsPrivate() {
        this.runTest(
            new TestDescriptor(
                "class C{private void m(){}}",
                "can not find private method",
                1,
                new OnlyPublicMethodsRule()
            )
        );
    }

    /**
     * Can find protected method.
     */
    @Test
    public void findsProtected() {
        this.runTest(
            new TestDescriptor(
                "class C{protected void m(){}}",
                "can not find protected method",
                1,
                new OnlyPublicMethodsRule()
            )
        );
    }

    /**
     * Can ignore public method.
     */
    @Test
    public void ignoresPublic() {
        this.runTest(
            new TestDescriptor(
                "class C{public void m(){}}",
                "can not ignore public method",
                0,
                new OnlyPublicMethodsRule()
            )
        );
    }
}
