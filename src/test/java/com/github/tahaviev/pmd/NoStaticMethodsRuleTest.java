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

import java.util.Arrays;
import java.util.List;

public class NoStaticMethodsRuleTest extends RuleTstFixed {

    @Test
    public void findsPublic() {
        this.runTest(
                new TestDescriptor(
                        "class C{public static R m(){}}",
                        "can not find public static method",
                        1,
                        new NoStaticMethodsRule()
                )
        );
    }

    @Test
    public void findsPackagePrivate() {
        this.runTest(
                new TestDescriptor(
                        "class C{static R m(){}}",
                        "can not find package private static method",
                        1,
                        new NoStaticMethodsRule()
                )
        );
    }

    @Test
    public void findsPrivate() {
        this.runTest(
                new TestDescriptor(
                        "class C{private static R m(){}}",
                        "can not find private static method",
                        1,
                        new NoStaticMethodsRule()
                )
        );
    }

    @Test
    public void findsInInterface() {
        this.runTest(
                new TestDescriptor(
                        "interface I{static R m(){}}",
                        "can not find static method in interface",
                        1,
                        new NoStaticMethodsRule()
                )
        );
    }

    @Test
    public void ignoresMainMethod() {
        this.runTest(
                new TestDescriptor(
                        "class C{public static void main(String[] args){}}",
                        "can not ignore main method",
                        0,
                        new NoStaticMethodsRule()
                )
        );
    }

    @Test
    public void ignoresVarargMainMethod() {
        this.runTest(
                new TestDescriptor(
                        "class C{public static void main(String... args){}}",
                        "can not ignore vararg main method",
                        0,
                        new NoStaticMethodsRule()
                )
        );
    }

    @Test
    public void findsMethodsSimilarToMain() {
        final List<String> calls = Arrays.asList(
                "private static void main(String[] args){}",
                "public static void main(){}",
                "public static void Main(String[] args){}",
                "public static void main(String args){}",
                "public static R main(String[] args){}",
                "public static void main(String s, String[] args){}"
        );
        this.runTest(
                new TestDescriptor(
                        String.format("class C{%s}", String.join("", calls)),
                        "can not find static methods similar to main",
                        calls.size(),
                        new NoStaticMethodsRule()
                )
        );
    }
}
