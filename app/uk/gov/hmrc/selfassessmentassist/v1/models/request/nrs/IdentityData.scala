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

package uk.gov.hmrc.selfassessmentassist.v1.models.request.nrs

import play.api.libs.json.{Json, OFormat, Reads, Writes}
import uk.gov.hmrc.auth.core.retrieve._
import uk.gov.hmrc.auth.core.{AffinityGroup, ConfidenceLevel, CredentialRole}
import uk.gov.hmrc.selfassessmentassist.utils.DateUtils

import java.time.LocalDate

case class IdentityData(internalId: Option[String] = None,
                        externalId: Option[String] = None,
                        agentCode: Option[String] = None,
                        credentials: Option[Credentials] = None,
                        confidenceLevel: ConfidenceLevel,
                        nino: Option[String] = None,
                        saUtr: Option[String] = None,
                        dateOfBirth: Option[LocalDate] = None,
                        email: Option[String] = None,
                        agentInformation: AgentInformation,
                        groupIdentifier: Option[String] = None,
                        credentialRole: Option[CredentialRole],
                        mdtpInformation: Option[MdtpInformation] = None,
                        itmpName: ItmpName,
                        itmpDateOfBirth: Option[LocalDate] = None,
                        itmpAddress: ItmpAddress,
                        affinityGroup: Option[AffinityGroup],
                        credentialStrength: Option[String] = None,
                        loginTimes: LoginTimes)

object IdentityData {
  implicit val localDateReads: Reads[LocalDate]           = DateUtils.dateReads
  implicit val localDateWrites: Writes[LocalDate]         = DateUtils.dateWrites
  implicit val credFormat: OFormat[Credentials]           = Json.format[Credentials]
  implicit val nameFormat: OFormat[Name]                  = Json.format[Name]
  implicit val agentInfoFormat: OFormat[AgentInformation] = Json.format[AgentInformation]
  implicit val mdtpInfoFormat: OFormat[MdtpInformation]   = Json.format[MdtpInformation]
  implicit val itmpNameFormat: OFormat[ItmpName]          = Json.format[ItmpName]
  implicit val itmpAddressFormat: OFormat[ItmpAddress]    = Json.format[ItmpAddress]
  implicit val loginTimesFormat: OFormat[LoginTimes]      = Json.format[LoginTimes]
  implicit val format: OFormat[IdentityData]              = Json.format[IdentityData]
}
