/*
 * Copyright © 2017 Mark Raynsford <code@io7m.com> https://www.io7m.com
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

import com.io7m.junreachable.UnreachableCodeException;

import java.util.Optional;

/**
 * Convenience functions over lexical positions.
 *
 * @since 1.1.0
 */

public final class LexicalPositions
{
  private LexicalPositions()
  {
    throw new UnreachableCodeException();
  }

  /**
   * Construct a lexical position at line 0, column 0, with no file.
   *
   * @param <F> The type of file names
   *
   * @return A lexical position
   */

  public static <F> LexicalPosition<F> zero()
  {
    return LexicalPosition.of(0, 0, Optional.empty());
  }

  /**
   * Construct a lexical position at line 0, column 0, with the given file.
   *
   * @param file The type name
   * @param <F>  The type of file names
   *
   * @return A lexical position
   */

  public static <F> LexicalPosition<F> zeroWithFile(
    final F file)
  {
    return LexicalPosition.of(0, 0, Optional.of(file));
  }
}
