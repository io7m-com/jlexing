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

import java.util.Optional;

/**
 * The type of mutable lexical positions.
 *
 * @param <F> The type of file names or paths
 */

public interface MutableLexicalPositionType<F> extends LexicalPositionType<F>
{
  /**
   * Set the line number.
   *
   * @param line The new line number
   */

  void setLine(int line);

  /**
   * Set the column number.
   *
   * @param column The new column number
   */

  void setColumn(int column);

  /**
   * Set the file.
   *
   * @param f The new file, if any
   */

  void setFile(Optional<F> f);
}
