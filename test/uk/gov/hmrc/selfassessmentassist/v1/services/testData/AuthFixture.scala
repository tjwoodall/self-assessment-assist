/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.selfassessmentassist.v1.services.testData

import uk.gov.hmrc.auth.core._
import uk.gov.hmrc.auth.core.retrieve._
import uk.gov.hmrc.selfassessmentassist.api.models.auth.UserDetails
import uk.gov.hmrc.selfassessmentassist.v1.models.request.nrs.IdentityData

import java.time.{Instant, LocalDate}

object AuthFixture {

  val enrolments: Enrolments =
    Enrolments(
      enrolments = Set(
        Enrolment(
          key = "MDTP-IT",
          identifiers = Seq(
            EnrolmentIdentifier(
              "UTR",
              "123"
            )
          ),
          state = "Activated"
        )
      )
    )

  val identityData: IdentityData =
    IdentityData(
      internalId = Some("Int-a7688cda-d983-472d-9971-ddca5f124641"),
      externalId = Some("Ext-c4ebc935-ac7a-4cc2-950a-19e6fac91f2a"),
      agentCode = None,
      credentials = Some(
        retrieve.Credentials(
          providerId = "8124873381064832",
          providerType = "GovernmentGateway"
        )),
      confidenceLevel = ConfidenceLevel.L200,
      nino = None,
      saUtr = None,
      dateOfBirth = None,
      email = Some("user@test.com"),
      agentInformation = AgentInformation(
        agentCode = None,
        agentFriendlyName = None,
        agentId = None
      ),
      groupIdentifier = Some("testGroupId-840cf4e3-c8ad-48f4-80fd-ea267f916be5"),
      credentialRole = Some(User),
      mdtpInformation = None,
      itmpName = ItmpName(givenName = Some("TestUser"), familyName = Some("TestUser"), middleName = None),
      itmpDateOfBirth = Some(LocalDate.parse("1990-04-16")),
      itmpAddress = ItmpAddress(
        line1 = Some("Add1"),
        line2 = None,
        line3 = None,
        line4 = None,
        line5 = None,
        postCode = Some("T11 S33"),
        countryName = None,
        countryCode = Some("UK")
      ),
      affinityGroup = Some(AffinityGroup.Individual),
      credentialStrength = Some("strong"),
      loginTimes = LoginTimes(
        currentLogin = Instant.parse("2018-04-16T11:00:55Z"),
        previousLogin = None
      )
    )

  val authResponse: (IdentityData, Enrolments) => Option[AffinityGroup] ~ Enrolments ~ Option[String] ~ Option[String] ~ Option[String] ~ Option[
    Credentials] ~ ConfidenceLevel ~ None.type ~ None.type ~ None.type ~ Option[String] ~ AgentInformation ~ Option[String] ~ Option[
    CredentialRole] ~ None.type ~ Option[String] ~ LoginTimes ~ Option[ItmpName] ~ Option[LocalDate] ~ Option[ItmpAddress] =
    (data, enrolments) =>
      new ~(
        new ~(
          new ~(
            new ~(
              new ~(
                new ~(
                  new ~(
                    new ~(
                      new ~(
                        new ~(
                          new ~(
                            new ~(
                              new ~(
                                new ~(
                                  new ~(
                                    new ~(new ~(new ~(new ~(data.affinityGroup, enrolments), data.internalId), data.externalId), data.agentCode),
                                    data.credentials),
                                  data.confidenceLevel),
                                None
                              ),
                              None
                            ),
                            None
                          ),
                          data.email
                        ),
                        data.agentInformation
                      ),
                      data.groupIdentifier
                    ),
                    data.credentialRole
                  ),
                  None
                ),
                data.credentialStrength
              ),
              data.loginTimes
            ),
            Some(data.itmpName)
          ),
          data.itmpDateOfBirth
        ),
        Some(data.itmpAddress)
      )

  val userDetails: (AffinityGroup, AgentInformation) => UserDetails = (Individual, agentInformation) =>
    UserDetails(
      userType = AffinityGroup.Individual,
      agentReferenceNumber = None,
      "",
      identityData = Option(
        IdentityData(
          internalId = Some("Int-a7688cda-d983-472d-9971-ddca5f124641"),
          externalId = Some("Ext-c4ebc935-ac7a-4cc2-950a-19e6fac91f2a"),
          agentCode = agentInformation.agentCode,
          credentials = Some(Credentials("8124873381064832", "GovernmentGateway")),
          confidenceLevel = ConfidenceLevel.L200,
          nino = None,
          saUtr = None,
          dateOfBirth = None,
          email = Some("user@test.com"),
          agentInformation = agentInformation,
          groupIdentifier = Some("testGroupId-840cf4e3-c8ad-48f4-80fd-ea267f916be5"),
          credentialRole = Some(User),
          mdtpInformation = None,
          itmpName = ItmpName(givenName = Some("TestUser"), familyName = Some("TestUser"), middleName = None),
          itmpDateOfBirth = Some(LocalDate.parse("1990-04-16")),
          itmpAddress = ItmpAddress(
            line1 = Some("Add1"),
            line2 = None,
            line3 = None,
            line4 = None,
            line5 = None,
            postCode = Some("T11 S33"),
            countryName = None,
            countryCode = Some("UK")),
          affinityGroup = Some(Individual),
          credentialStrength = Some("strong"),
          loginTimes = LoginTimes(currentLogin = Instant.parse("2018-04-16T11:00:55.000Z"), previousLogin = None)
        ))
    )

}
