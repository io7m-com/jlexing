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

package com.io7m.jlexing.core;

import com.io7m.jnull.NullCheck;
import net.jcip.annotations.Immutable;

import java.util.Optional;

/**
 * The default implementation of the {@link ImmutableLexicalPositionType}
 * interface.
 *
 * @param <F> The type of file names or paths
 */

@Immutable public final class ImmutableLexicalPosition<F>
  implements ImmutableLexicalPositionType<F>
{
  private final int         line;
  private final int         column;
  private final Optional<F> file;

  private ImmutableLexicalPosition(
    final int in_line,
    final int in_column,
    final Optional<F> in_file)
  {
    this.column = in_column;
    this.line = in_line;
    this.file = NullCheck.notNull(in_file);
  }

  /**
   * Construct a new position.
   *
   * @param column The file column
   * @param line   The file line
   * @param file   The file name or path
   * @param <F>    The type of file names or paths
   *
   * @return A new position
   */

  public static <F> ImmutableLexicalPositionType<F> newPositionWithFile(
    final int line,
    final int column,
    final F file)
  {
    return new ImmutableLexicalPosition<>(line, column, Optional.of(file));
  }

  /**
   * Construct a new position.
   *
   * @param column The file column
   * @param line   The file line
   * @param <F>    The type of file names or paths
   *
   * @return A new position
   */

  public static <F> ImmutableLexicalPositionType<F> newPosition(
    final int line,
    final int column)
  {
    return new ImmutableLexicalPosition<>(line, column, Optional.empty());
  }

  /**
   * Construct a new position.
   *
   * @param p   An existing position value
   * @param <F> The type of file names or paths
   *
   * @return A new position
   */

  public static <F> ImmutableLexicalPositionType<F> newFrom(
    final LexicalPositionType<F> p)
  {
    return new ImmutableLexicalPosition<>(
      p.getLine(), p.getColumn(), p.getFile());
  }

  @Override public boolean equals(final Object o)
  {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }

    final ImmutableLexicalPosition<?> that = (ImmutableLexicalPosition<?>) o;
    return this.line == that.line
           && this.column == that.column
           && this.file.equals(that.file);
  }

  @Override public int hashCode()
  {
    int result = this.line;
    result = 31 * result + this.column;
    result = 31 * result + this.file.hashCode();
    return result;
  }

  @Override public String toString()
  {
    final StringBuilder sb = new StringBuilder(64);
    if (this.file.isPresent()) {
      sb.append(this.file.get());
      sb.append(":");
    }
    sb.append(this.line);
    sb.append(":");
    sb.append(this.column);
    return sb.toString();
  }

  @Override public int getLine()
  {
    return this.line;
  }

  @Override public int getColumn()
  {
    return this.column;
  }

  @Override public Optional<F> getFile()
  {
    return this.file;
  }
}
