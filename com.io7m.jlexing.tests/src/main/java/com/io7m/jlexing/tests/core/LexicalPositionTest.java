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
import com.io7m.jlexing.core.LexicalPositionType;
import com.io7m.jlexing.core.LexicalPositions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public final class LexicalPositionTest
{
  @Rule public final ExpectedException expected = ExpectedException.none();

  @Test
  public void testEquals()
  {
    final Path path0 = Paths.get("/example0");
    final Path path1 = Paths.get("/example1");

    Assert.assertEquals(
      LexicalPosition.of(1, 0, Optional.of(path0)),
      LexicalPosition.of(1, 0, Optional.of(path0)));

    Assert.assertNotEquals(
      LexicalPosition.of(2, 0, Optional.of(path0)),
      LexicalPosition.of(1, 0, Optional.of(path0)));

    Assert.assertNotEquals(
      LexicalPosition.of(1, 1, Optional.of(path0)),
      LexicalPosition.of(1, 0, Optional.of(path0)));

    Assert.assertNotEquals(
      LexicalPosition.of(1, 0, Optional.of(path1)),
      LexicalPosition.of(1, 0, Optional.of(path0)));

    Assert.assertEquals(
      1L,
      (long) LexicalPosition.of(1, 0, Optional.of(path0)).line());
    Assert.assertEquals(
      0L,
      (long) LexicalPosition.of(1, 0, Optional.of(path0)).column());
    Assert.assertEquals(
      Optional.of(path0),
      LexicalPosition.of(1, 0, Optional.of(path0)).file());
  }

  @Test
  public void testToString()
  {
    final Path path0 = Paths.get("/example0");
    final Path path1 = Paths.get("/example1");

    Assert.assertEquals(
      LexicalPosition.of(1, 0, Optional.of(path0)).toString(),
      LexicalPosition.of(1, 0, Optional.of(path0)).toString());

    Assert.assertNotEquals(
      LexicalPosition.of(2, 0, Optional.of(path0)).toString(),
      LexicalPosition.of(1, 0, Optional.of(path0)).toString());

    Assert.assertNotEquals(
      LexicalPosition.of(1, 1, Optional.of(path0)).toString(),
      LexicalPosition.of(1, 0, Optional.of(path0)).toString());

    Assert.assertNotEquals(
      LexicalPosition.of(1, 0, Optional.of(path1)).toString(),
      LexicalPosition.of(1, 0, Optional.of(path0)).toString());
  }

  @Test
  public void testHashCode()
  {
    final Path path0 = Paths.get("/example0");
    final Path path1 = Paths.get("/example1");

    Assert.assertEquals(
      (long) LexicalPosition.of(1, 0, Optional.of(path0)).hashCode(),
      (long) LexicalPosition.of(1, 0, Optional.of(path0)).hashCode());

    Assert.assertNotEquals(
      (long) LexicalPosition.of(2, 0, Optional.of(path0)).hashCode(),
      (long) LexicalPosition.of(1, 0, Optional.of(path0)).hashCode());

    Assert.assertNotEquals(
      (long) LexicalPosition.of(1, 1, Optional.of(path0)).hashCode(),
      (long) LexicalPosition.of(1, 0, Optional.of(path0)).hashCode());

    Assert.assertNotEquals(
      (long) LexicalPosition.of(1, 0, Optional.of(path1)).hashCode(),
      (long) LexicalPosition.of(1, 0, Optional.of(path0)).hashCode());
  }

  @Test
  public void testWith()
  {
    final Path path0 = Paths.get("/example0");
    final Path path1 = Paths.get("/example1");

    final LexicalPosition<Path> base =
      LexicalPosition.of(1, 0, Optional.of(path0));

    Assert.assertEquals(23L, (long) base.withLine(23).line());
    Assert.assertEquals(1L, (long) base.withLine(1).line());

    Assert.assertEquals(25L, (long) base.withColumn(25).column());
    Assert.assertEquals(0L, (long) base.withColumn(0).column());

    Assert.assertEquals(Optional.of(path1), base.withFile(path1).file());
    Assert.assertEquals(Optional.of(path0), base.withFile(path0).file());

    Assert.assertEquals(
      Optional.of(path1), base.withFile(Optional.of(path1)).file());
    Assert.assertEquals(
      Optional.of(path0), base.withFile(Optional.of(path0)).file());
  }

  @Test
  public void testCopy()
  {
    final Path path0 = Paths.get("/example0");

    final LexicalPosition<Path> base =
      LexicalPosition.of(1, 0, Optional.of(path0));

    Assert.assertEquals(base, LexicalPosition.copyOf(base));
    Assert.assertEquals(base, LexicalPosition.copyOf(new LexicalPositionType<Path>() {
      @Override
      public int line()
      {
        return 1;
      }

      @Override
      public int column()
      {
        return 0;
      }

      @Override
      public Optional<Path> file()
      {
        return Optional.of(path0);
      }
    }));
  }

  @Test
  public void testBuilder0()
  {
    final LexicalPosition.Builder<Path> b = LexicalPosition.builder();

    this.expected.expect(IllegalStateException.class);
    b.build();
  }

  @Test
  public void testBuilder1()
  {
    final Path path0 = Paths.get("/example0");
    final Path path1 = Paths.get("/example1");

    final LexicalPosition.Builder<Path> b0 = LexicalPosition.builder();

    b0.setLine(23);
    b0.setColumn(24);
    b0.setFile(Optional.of(path0));
    b0.setFile(path1);
    final LexicalPosition<Path> p = b0.build();

    Assert.assertEquals(23L, (long) p.line());
    Assert.assertEquals(24L, (long) p.column());
    Assert.assertEquals(Optional.of(path1), p.file());

    final LexicalPosition.Builder<Path> b1 = LexicalPosition.builder();
    b1.from(p);

    Assert.assertEquals(p, b1.build());
  }

  @Test
  public void testZero()
  {
    Assert.assertEquals(
      LexicalPosition.of(0, 0, Optional.empty()),
      LexicalPositions.zero());
  }

  @Test
  public void testZeroWithFile()
  {
    Assert.assertEquals(
      LexicalPosition.of(0, 0, Optional.of(Integer.valueOf(23))),
      LexicalPositions.zeroWithFile(Integer.valueOf(23)));
  }
}
