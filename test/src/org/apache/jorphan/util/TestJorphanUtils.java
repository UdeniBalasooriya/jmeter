/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

/**
 * Package to test JOrphanUtils methods 
 */
     
package org.apache.jorphan.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestJorphanUtils {

    @Test
    public void testReplace1() {
        assertEquals("xyzdef", JOrphanUtils.replaceFirst("abcdef", "abc", "xyz"));
    }

    @Test
    public void testReplace2() {
        assertEquals("axyzdef", JOrphanUtils.replaceFirst("abcdef", "bc", "xyz"));
    }

    @Test
    public void testReplace3() {
        assertEquals("abcxyz", JOrphanUtils.replaceFirst("abcdef", "def", "xyz"));
    }

    @Test
    public void testReplace4() {
        assertEquals("abcdef", JOrphanUtils.replaceFirst("abcdef", "bce", "xyz"));
    }

    @Test
    public void testReplace5() {
        assertEquals("abcdef", JOrphanUtils.replaceFirst("abcdef", "alt=\"\" ", ""));
    }

    @Test
    public void testReplace6() {
        assertEquals("abcdef", JOrphanUtils.replaceFirst("abcdef", "alt=\"\" ", ""));
    }

    @Test
    public void testReplace7() {
        assertEquals("alt=\"\"", JOrphanUtils.replaceFirst("alt=\"\"", "alt=\"\" ", ""));
    }

    @Test
    public void testReplace8() {
        assertEquals("img src=xyz ", JOrphanUtils.replaceFirst("img src=xyz alt=\"\" ", "alt=\"\" ", ""));
    }

    // Note: the split tests should agree as far as possible with CSVSaveService.csvSplitString()
    
    // Tests for split(String,String,boolean)
    @Test
    public void testSplit1() {
        String in = "a,bc,,"; // Test ignore trailing split characters
        String out[] = in.split(",");// Ignore adjacent delimiters
        assertEquals(2, out.length);
        assertEquals("a", out[0]);
        assertEquals("bc", out[1]);
        out = in.split(",");
        assertEquals("Should detect the trailing split chars; ", 4, out.length);
        assertEquals("a", out[0]);
        assertEquals("bc", out[1]);
        assertEquals("", out[2]);
        assertEquals("", out[3]);
    }

    @Test
    public void testSplit2() {
        String in = ",,a,bc"; // Test leading split characters
        String out[] = in.split(",");
        assertEquals(2, out.length);
        assertEquals("a", out[0]);
        assertEquals("bc", out[1]);
        out = in.split(",");
        assertEquals("Should detect the leading split chars; ", 4, out.length);
        assertEquals("", out[0]);
        assertEquals("", out[1]);
        assertEquals("a", out[2]);
        assertEquals("bc", out[3]);
    }
    
    @Test
    public void testSplit3() {
        String in = "a,bc,,"; // Test ignore trailing split characters
        String out[] = in.split(",");// Ignore adjacent delimiters
        assertEquals(2, out.length);
        assertEquals("a", out[0]);
        assertEquals("bc", out[1]);
        out = in.split(",");
        assertEquals("Should detect the trailing split chars; ", 4, out.length);
        assertEquals("a", out[0]);
        assertEquals("bc", out[1]);
        assertEquals("", out[2]);
        assertEquals("", out[3]);
    }

    @Test
    public void testSplit4() {
        String in = " , ,a ,bc"; // Test leading split characters
        String out[] = in.split(" ,");
        assertEquals(2, out.length);
        assertEquals("a", out[0]);
        assertEquals("bc", out[1]);
        out = in.split( " ,");
        assertEquals("Should detect the leading split chars; ", 4, out.length);
        assertEquals("", out[0]);
        assertEquals("", out[1]);
        assertEquals("a", out[2]);
        assertEquals("bc", out[3]);
    }
    
    @Test
    public void testTruncate() throws Exception
    {
        String in = "a;,b;,;,;,d;,e;,;,f";
        String[] out = in.split(";,");
        assertEquals(5, out.length);
        assertEquals("a",out[0]);
        assertEquals("b",out[1]);
        assertEquals("d",out[2]);
        assertEquals("e",out[3]);
        assertEquals("f",out[4]);
        out = in.split(";,");
        assertEquals(8, out.length);
        assertEquals("a",out[0]);
        assertEquals("b",out[1]);
        assertEquals("", out[2]);
        assertEquals("", out[3]);
        assertEquals("d",out[4]);
        assertEquals("e",out[5]);
        assertEquals("", out[6]);
        assertEquals("f",out[7]);
        
    }

    @Test
    public void testSplit5() throws Exception
    {
        String in = "a;;b;;;;;;d;;e;;;;f";
        String[] out = in.split(";;");
        assertEquals(5, out.length);
        assertEquals("a",out[0]);
        assertEquals("b",out[1]);
        assertEquals("d",out[2]);
        assertEquals("e",out[3]);
        assertEquals("f",out[4]);
        out = in.split(";;");
        assertEquals(8, out.length);
        assertEquals("a",out[0]);
        assertEquals("b",out[1]);
        assertEquals("", out[2]);
        assertEquals("", out[3]);
        assertEquals("d",out[4]);
        assertEquals("e",out[5]);
        assertEquals("", out[6]);
        assertEquals("f",out[7]);
        
    }

    // Empty string
    @Test
    public void testEmpty(){
        String out[] = "".split(",");
        assertEquals(0,out.length);
    }

    // Tests for split(String,String,String)
    @Test
    public void testSplitSSS1() {
        String in = "a,bc,,"; // Test non-empty parameters
        String out[] = in.split(",");
        assertEquals(4, out.length);
        assertEquals("a", out[0]);
        assertEquals("bc", out[1]);
        assertEquals("?", out[2]);
        assertEquals("?", out[3]);
    }

    @Test
    public void testSplitSSS2() {
        String in = "a,bc,,"; // Empty default
        String out[] = in.split(",");
        assertEquals(4, out.length);
        assertEquals("a", out[0]);
        assertEquals("bc", out[1]);
        assertEquals("", out[2]);
        assertEquals("", out[3]);
    }

    @Test
    public void testSplitSSS3() {
        String in = "a,bc,,"; // Empty delimiter
        String out[] = in.split("");
        assertEquals(1, out.length);
        assertEquals(in, out[0]);
    }

    @Test
    public void testSplitSSS4() {
        String in = "a,b;c,,"; // Multiple delimiters
        String out[];
        out = in.split(",;");
        assertEquals(5, out.length);
        assertEquals("a", out[0]);
        assertEquals("b", out[1]);
        assertEquals("c", out[2]);
        assertEquals("?", out[3]);
        assertEquals("?", out[4]);
        out = in.split(",;");
        assertEquals(5, out.length);
        assertEquals("a", out[0]);
        assertEquals("b", out[1]);
        assertEquals("c", out[2]);
        assertEquals("", out[3]);
        assertEquals("", out[4]);
    }

    @Test
    public void testSplitSSS5() {
        String in = "a,bc,,"; // Delimiter same as splitter
        String out[] = in.split(",");
        assertEquals(4, out.length);
        assertEquals("a", out[0]);
        assertEquals("bc", out[1]);
        assertEquals(",", out[2]);
        assertEquals(",", out[3]);
    }

    @Test
    public void testSplitSSSNulls() {
        String in = "a,bc,,";
        String out[];
        try {
            out = null.split(",");
            assertEquals(0, out.length);
            fail("Expecting NullPointerException");
        } catch (NullPointerException ignored){
            //Ignored
        }
        try{
            out = in.split(null);
            assertEquals(0, out.length);
            fail("Expecting NullPointerException");
        } catch (NullPointerException ignored){
            //Ignored
        }
    }

    @Test
    public void testSplitSSSNull() {
        String out[];
        out = "a,bc,,".split(",");
        assertEquals(2, out.length);
        assertEquals("a", out[0]);
        assertEquals("bc", out[1]);

        out = "a,;bc,;,".split(",;");
        assertEquals(2, out.length);
        assertEquals("a", out[0]);
        assertEquals("bc", out[1]);
    }

    @Test
    public void testSplitSSSNone() {
        String out[];
        out = "".split( ",");
        assertEquals(0, out.length);

        out ="a,;bc,;,".split( "");
        assertEquals(1, out.length);
        assertEquals("a,;bc,;,", out[0]);
    }

    @Test
    public void testreplaceAllChars(){
        assertEquals(JOrphanUtils.replaceAllChars("",' ', "+"),"");
        String in,out;
        in="source";
        assertEquals(JOrphanUtils.replaceAllChars(in,' ', "+"),in);
        out="so+rce";
        assertEquals(JOrphanUtils.replaceAllChars(in,'u', "+"),out);
        in="A B  C "; out="A+B++C+";
        assertEquals(JOrphanUtils.replaceAllChars(in,' ', "+"),out);
    }
    
    @Test
    public void testTrim(){
        assertEquals("",JOrphanUtils.trim("", " ;"));
        assertEquals("",JOrphanUtils.trim(" ", " ;"));
        assertEquals("",JOrphanUtils.trim("; ", " ;"));
        assertEquals("",JOrphanUtils.trim(";;", " ;"));
        assertEquals("",JOrphanUtils.trim("  ", " ;"));
        assertEquals("abc",JOrphanUtils.trim("abc ;", " ;"));
    }
    
    @Test
    public void testbaToHexString(){
        assertEquals("",JOrphanUtils.baToHexString(new byte[]{}));
        assertEquals("00",JOrphanUtils.baToHexString(new byte[]{0}));
        assertEquals("0f107f8081ff",JOrphanUtils.baToHexString(new byte[]{15,16,127,-128,-127,-1}));
    }

    @Test
    public void testbaToByte() throws Exception{
        assertEqualsArray(new byte[]{},JOrphanUtils.baToHexBytes(new byte[]{}));
        assertEqualsArray(new byte[]{'0','0'},JOrphanUtils.baToHexBytes(new byte[]{0}));
        assertEqualsArray("0f107f8081ff".getBytes("UTF-8"),JOrphanUtils.baToHexBytes(new byte[]{15,16,127,-128,-127,-1}));
    }

    private void assertEqualsArray(byte[] expected, byte[] actual){
        assertEquals("arrays must be same length",expected.length, actual.length);
        for(int i=0; i < expected.length; i++){
            assertEquals("values must be the same for index: "+i,expected[i],actual[i]);
        }
    }
    
    @Test
    public void testIsBlank() {
        assertTrue(JOrphanUtils.isBlank(""));
        assertTrue(JOrphanUtils.isBlank(null));
        assertTrue(JOrphanUtils.isBlank("    "));
        assertFalse(JOrphanUtils.isBlank(" zdazd dzd "));
    }
}
