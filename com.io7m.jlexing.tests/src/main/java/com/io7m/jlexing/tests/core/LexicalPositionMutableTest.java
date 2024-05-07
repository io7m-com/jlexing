/*
 * Copyright © 2016 <code@io7m.com> http://io7m.com
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

import com.io7m.jlexing.core.LexicalPosition;
import com.io7m.jlexing.core.LexicalPositionMutable;
import com.io7m.jlexing.core.LexicalPositionType;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public final class LexicalPositionMutableTest
{
  @Rule public final ExpectedException expected = ExpectedException.none();

  @Test
  public void testEquals()
  {
    final Path path0 = Paths.get("/example0");
    final Path path1 = Paths.get("/example1");

    final LexicalPositionMutable<Path> base0_a =
      LexicalPositionMutable.create(1, 0, Optional.of(path0));
    final LexicalPositionMutable<Path> base0_b =
      LexicalPositionMutable.create(1, 0, Optional.of(path0));
    final LexicalPositionMutable<Path> base1_a =
      LexicalPositionMutable.create(2, 0, Optional.of(path0));
    final LexicalPositionMutable<Path> base2_a =
      LexicalPositionMutable.create(1, 1, Optional.of(path0));
    final LexicalPositionMutable<Path> base3_a =
      LexicalPositionMutable.create(1, 0, Optional.of(path1));

    Assert.assertEquals(base0_a, base0_a);
    Assert.assertEquals(base0_a, base0_b);
    Assert.assertEquals(base0_b, base0_a);

    Assert.assertNotEquals(base1_a, base0_a);
    Assert.assertNotEquals(base2_a, base0_a);
    Assert.assertNotEquals(base3_a, base0_a);

    Assert.assertEquals(
      1L,
      (long) base0_a.line());
    Assert.assertEquals(
      0L,
      (long) base0_a.column());
    Assert.assertEquals(
      Optional.of(path0),
      base0_a.file());

    Assert.assertNotEquals(
      LexicalPositionMutable.create(2, 0, Optional.of(path0)),
      LexicalPositionMutable.create());
  }

  @Test
  public void testToString()
  {
    final Path path0 = Paths.get("/example0");
    final Path path1 = Paths.get("/example1");

    Assert.assertEquals(
      LexicalPositionMutable.create(1, 0, Optional.of(path0)).toString(),
      LexicalPositionMutable.create(1, 0, Optional.of(path0)).toString());

    Assert.assertNotEquals(
      LexicalPositionMutable.create(2, 0, Optional.of(path0)).toString(),
      LexicalPositionMutable.create(1, 0, Optional.of(path0)).toString());

    Assert.assertNotEquals(
      LexicalPositionMutable.create(1, 1, Optional.of(path0)).toString(),
      LexicalPositionMutable.create(1, 0, Optional.of(path0)).toString());

    Assert.assertNotEquals(
      LexicalPositionMutable.create(1, 0, Optional.of(path1)).toString(),
      LexicalPositionMutable.create(1, 0, Optional.of(path0)).toString());
  }

  @Test
  public void testHashCode()
  {
    final Path path0 = Paths.get("/example0");
    final Path path1 = Paths.get("/example1");

    Assert.assertEquals(
      (long) LexicalPositionMutable.create(1, 0, Optional.of(path0)).hashCode(),
      (long) LexicalPositionMutable.create(1, 0, Optional.of(path0)).hashCode());

    Assert.assertNotEquals(
      (long) LexicalPositionMutable.create(2, 0, Optional.of(path0)).hashCode(),
      (long) LexicalPositionMutable.create(1, 0, Optional.of(path0)).hashCode());

    Assert.assertNotEquals(
      (long) LexicalPositionMutable.create(1, 1, Optional.of(path0)).hashCode(),
      (long) LexicalPositionMutable.create(1, 0, Optional.of(path0)).hashCode());

    Assert.assertNotEquals(
      (long) LexicalPositionMutable.create(1, 0, Optional.of(path1)).hashCode(),
      (long) LexicalPositionMutable.create(1, 0, Optional.of(path0)).hashCode());
  }

  @Test
  public void testWith()
  {
    final Path path0 = Paths.get("/example0");
    final Path path1 = Paths.get("/example1");

    final LexicalPositionMutable<Path> base =
      LexicalPositionMutable.create(1, 0, Optional.of(path0));

    Assert.assertEquals(23L, (long) base.setLine(23).line());
    Assert.assertEquals(1L, (long) base.setLine(1).line());

    Assert.assertEquals(25L, (long) base.setColumn(25).column());
    Assert.assertEquals(0L, (long) base.setColumn(0).column());

    Assert.assertEquals(Optional.of(path1), base.setFile(path1).file());
    Assert.assertEquals(Optional.of(path0), base.setFile(path0).file());

    Assert.assertEquals(
      Optional.of(path1), base.setFile(Optional.of(path1)).file());
    Assert.assertEquals(
      Optional.of(path0), base.setFile(Optional.of(path0)).file());
  }

  @Test
  public void testCopy()
  {
    final Path path0 = Paths.get("/example0");

    final LexicalPositionMutable<Path> base0 =
      LexicalPositionMutable.create(1, 0, Optional.of(path0));
    final LexicalPositionMutable<Path> base1 =
      LexicalPositionMutable.create();

    base1.from(base0);

    Assert.assertEquals(base0, base1);
  }

  @Test
  public void testLine()
  {
    final LexicalPositionMutable<Path> base0 =
      LexicalPositionMutable.create();

    Assert.assertFalse(base0.lineIsSet());

    this.expected.expect(IllegalStateException.class);
    base0.line();
  }

  @Test
  public void testLineUnset()
  {
    final LexicalPositionMutable<Path> base0 =
      LexicalPositionMutable.create();

    base0.setLine(23);
    base0.line();
    Assert.assertTrue(base0.lineIsSet());
    base0.unsetLine();
    Assert.assertFalse(base0.lineIsSet());

    this.expected.expect(IllegalStateException.class);
    base0.line();
  }

  @Test
  public void testColumn()
  {
    final LexicalPositionMutable<Path> base0 =
      LexicalPositionMutable.create();

    Assert.assertFalse(base0.columnIsSet());

    this.expected.expect(IllegalStateException.class);
    base0.column();
  }

  @Test
  public void testColumnUnset()
  {
    final LexicalPositionMutable<Path> base0 =
      LexicalPositionMutable.create();

    base0.setColumn(23);
    base0.column();
    Assert.assertTrue(base0.columnIsSet());
    base0.unsetColumn();
    Assert.assertFalse(base0.columnIsSet());

    this.expected.expect(IllegalStateException.class);
    base0.column();
  }

  @Test
  public void testImmutable()
  {
    final LexicalPositionMutable<Path> base0 =
      LexicalPositionMutable.create(1, 0, Optional.of(Paths.get("/ex0")));
    final LexicalPosition<Path> base1 =
      LexicalPosition.of(1, 0, Optional.of(Paths.get("/ex0")));

    Assert.assertEquals(base1, base0.toImmutable());
  }

  @Test
  public void testClear()
  {
    final LexicalPositionMutable<Path> base0 =
      LexicalPositionMutable.create(1, 0, Optional.of(Paths.get("/ex0")));

    Assert.assertTrue(base0.columnIsSet());
    Assert.assertTrue(base0.lineIsSet());
    Assert.assertTrue(base0.isInitialized());

    base0.clear();

    Assert.assertFalse(base0.columnIsSet());
    Assert.assertFalse(base0.lineIsSet());
    Assert.assertFalse(base0.isInitialized());
  }
}
