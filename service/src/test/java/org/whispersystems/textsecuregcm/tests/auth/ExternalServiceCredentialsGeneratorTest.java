/*
 * Copyright 2013-2020 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.tests.auth;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;
import org.whispersystems.textsecuregcm.auth.ExternalServiceCredentialGenerator;
import org.whispersystems.textsecuregcm.auth.ExternalServiceCredentials;

public class ExternalServiceCredentialsGeneratorTest {

  @Test
  public void testGenerateDerivedUsername() {
    ExternalServiceCredentialGenerator generator = new ExternalServiceCredentialGenerator(new byte[32], new byte[32]);
    ExternalServiceCredentials credentials = generator.generateFor("+14152222222");

    assertThat(credentials.getUsername()).isNotEqualTo("+14152222222");
    assertThat(credentials.getPassword().startsWith("+14152222222")).isFalse();
  }

  @Test
  public void testGenerateNoDerivedUsername() {
    ExternalServiceCredentialGenerator generator = new ExternalServiceCredentialGenerator(new byte[32], new byte[32], false);
    ExternalServiceCredentials credentials = generator.generateFor("+14152222222");

    assertThat(credentials.getUsername()).isEqualTo("+14152222222");
    assertThat(credentials.getPassword().startsWith("+14152222222")).isTrue();
  }

}
