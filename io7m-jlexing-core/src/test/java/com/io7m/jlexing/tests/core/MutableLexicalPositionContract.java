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

import com.io7m.jlexing.core.MutableLexicalPositionType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public abstract class MutableLexicalPositionContract<F, T extends
  MutableLexicalPositionType<F>>
  extends LexicalPositionContract<F, T>
{
  @Test public final void testSetLine()
  {
    final T x = this.newPosition();
    Assert.assertEquals(0L, (long) x.getLine());
    Assert.assertEquals(0L, (long) x.getColumn());
    Assert.assertFalse(x.getFile().isPresent());

    x.setLine(101);
    Assert.assertEquals(101L, (long) x.getLine());
    Assert.assertEquals(0L, (long) x.getColumn());
    Assert.assertFalse(x.getFile().isPresent());
  }

  @Test public final void testSetColumn()
  {
    final T x = this.newPosition();
    Assert.assertEquals(0L, (long) x.getLine());
    Assert.assertEquals(0L, (long) x.getColumn());
    Assert.assertFalse(x.getFile().isPresent());

    x.setColumn(101);
    Assert.assertEquals(0L, (long) x.getLine());
    Assert.assertEquals(101L, (long) x.getColumn());
    Assert.assertFalse(x.getFile().isPresent());
  }

  @Test public final void testSetFile()
  {
    final T x = this.newPosition();
    Assert.assertEquals(0L, (long) x.getLine());
    Assert.assertEquals(0L, (long) x.getColumn());
    Assert.assertFalse(x.getFile().isPresent());

    final F f = this.newFile("file.txt");
    x.setFile(Optional.of(f));
    Assert.assertEquals(0L, (long) x.getLine());
    Assert.assertEquals(0L, (long) x.getColumn());
    final Optional<F> file = x.getFile();
    Assert.assertTrue(file.isPresent());
    Assert.assertEquals(f, file.get());
  }
}
