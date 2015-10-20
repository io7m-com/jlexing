/*
 * Copyright © 2015 <code@io7m.com> http://io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.jlexing.tests.core;

import com.io7m.jlexing.core.LexicalPositionType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public abstract class LexicalPositionContract<F, T extends
  LexicalPositionType<F>>
{
  protected abstract T newPositionFrom(T x);

  protected abstract T newPosition();

  protected abstract T newPositionLC(
    int line,
    int column);

  protected abstract T newPositionLCWith(
    int line,
    int column,
    F f);

  protected abstract T newPositionWith(F f);

  protected abstract F newFile(String name);

  @Test public final void testNew0()
  {
    final T x = this.newPosition();
    Assert.assertEquals(0L, (long) x.getLine());
    Assert.assertEquals(0L, (long) x.getColumn());
    Assert.assertFalse(x.getFile().isPresent());
  }

  @Test public final void testNew1()
  {
    final T x = this.newPositionLC(23, 32);
    Assert.assertEquals(23L, (long) x.getLine());
    Assert.assertEquals(32L, (long) x.getColumn());
    Assert.assertFalse(x.getFile().isPresent());
  }

  @Test public final void testNew2()
  {
    final F f = this.newFile("hello.txt");
    final T x = this.newPositionLCWith(23, 32, f);
    Assert.assertEquals(23L, (long) x.getLine());
    Assert.assertEquals(32L, (long) x.getColumn());

    final Optional<F> f_opt = x.getFile();
    Assert.assertTrue(f_opt.isPresent());
    Assert.assertEquals(f, f_opt.get());
  }

  @Test public final void testNewFrom()
  {
    final F f = this.newFile("hello.txt");
    final T x = this.newPositionLCWith(23, 32, f);
    final T y = this.newPositionFrom(x);
    Assert.assertEquals(x, y);
    Assert.assertNotSame(x, y);
  }

  @Test public final void testToString0()
  {
    final T x = this.newPosition();
    Assert.assertEquals("0:0", x.toString());
  }

  @Test public final void testToString1()
  {
    final T x = this.newPositionWith(this.newFile("file.txt"));
    Assert.assertEquals("file.txt:0:0", x.toString());
  }

  @Test public final void testEquals()
  {
    final F f = this.newFile("file.txt");
    final F g = this.newFile("file2.txt");
    final T x0 = this.newPositionLCWith(23, 32, f);
    final T x1 = this.newPositionLCWith(23, 32, f);
    final T x2 = this.newPositionLCWith(23, 32, f);

    // Reflexive
    Assert.assertEquals(x0, x0);

    // Symmetric
    Assert.assertEquals(x0, x1);
    Assert.assertEquals(x1, x0);

    // Transitive
    Assert.assertEquals(x0, x1);
    Assert.assertEquals(x1, x2);
    Assert.assertEquals(x0, x2);

    // Inequality cases
    Assert.assertNotEquals(x0, null);
    Assert.assertNotEquals(x0, Integer.valueOf(23));
    Assert.assertNotEquals(x0, this.newPositionLCWith(0, 32, f));
    Assert.assertNotEquals(x0, this.newPositionLCWith(23, 0, f));
    Assert.assertNotEquals(x0, this.newPositionLCWith(23, 32, g));
    Assert.assertNotEquals(x0, this.newPositionLC(23, 32));

    // Hashcode
    Assert.assertEquals((long) x0.hashCode(), (long) x0.hashCode());
  }

  @Test public final void testCompare()
  {
    final T x0 = this.newPositionLC(0, 0);
    final T x1 = this.newPositionLC(1, 0);
    final T x2 = this.newPositionLC(0, 1);

    Assert.assertEquals(0L, (long) x0.compareTo(x0));
    Assert.assertTrue(x0.compareTo(x1) < 0);
    Assert.assertTrue(x0.compareTo(x2) < 0);

    Assert.assertTrue(x1.compareTo(x0) > 0);
    Assert.assertTrue(x1.compareTo(x2) > 0);

    Assert.assertTrue(x2.compareTo(x0) > 0);
    Assert.assertTrue(x2.compareTo(x1) < 0);
  }
}
