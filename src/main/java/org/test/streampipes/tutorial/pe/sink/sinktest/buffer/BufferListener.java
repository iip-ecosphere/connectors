// SPDX-FileCopyrightText: 2023 Siemens AG
// SPDX-License-Identifier: Apache-2.0

package org.test.streampipes.tutorial.pe.sink.sinktest.buffer;

public interface BufferListener {

	void bufferFull(String messagesJsonArray);

}
