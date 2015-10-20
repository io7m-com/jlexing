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
import com.io7m.jnull.Nullable;

import java.util.Optional;

/**
 * The type of lexical positions.
 *
 * @param <F> The type of file names or paths
 */

public interface LexicalPositionType<F>
  extends Comparable<LexicalPositionType<F>>
{
  /**
   * @return The line number
   */

  int getLine();

  /**
   * @return The column number
   */

  int getColumn();

  /**
   * @return The file, if any
   */

  Optional<F> getFile();

  @Override default int compareTo(final @Nullable LexicalPositionType<F> o)
  {
    /**
     * Lexical positions are simply compared by line and column number, with
     * files being ignored.
     */

    // CHECKSTYLE:OFF
    final LexicalPositionType<F> on = NullCheck.notNull(o);
    final int lc = Integer.compareUnsigned(this.getLine(), on.getLine());
    if (lc == 0) {
      return Integer.compareUnsigned(this.getColumn(), on.getColumn());
    }
    return lc;
    // CHECKSTYLE:ON
  }
}
