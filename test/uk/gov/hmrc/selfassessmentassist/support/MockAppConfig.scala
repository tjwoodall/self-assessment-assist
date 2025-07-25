/*
 * Copyright 2025 HM Revenue & Customs
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

package uk.gov.hmrc.selfassessmentassist.support

import org.scalamock.handlers.{CallHandler, CallHandler0}
import org.scalamock.scalatest.MockFactory
import org.scalatest.TestSuite
import play.api.Configuration
import uk.gov.hmrc.selfassessmentassist.api.models.auth.AuthCredential
import uk.gov.hmrc.selfassessmentassist.config.{AppConfig, ConfidenceLevelConfig}

import java.time.Duration
import scala.concurrent.duration.FiniteDuration

trait MockAppConfig extends TestSuite with MockFactory {

  implicit val mockAppConfig: AppConfig = mock[AppConfig]

  object MockedAppConfig {

    // MTD ID Lookup Config
    def mtdIdBaseUrl: CallHandler[String] = (() => mockAppConfig.mtdIdBaseUrl: String).expects()
    // RDS config items
    def rdsBaseUrlForSubmit: CallHandler[String]        = (() => mockAppConfig.rdsBaseUrlForSubmit).expects().anyNumberOfTimes()
    def rdsBaseUrlForAcknowledge: CallHandler[String]   = (() => mockAppConfig.rdsBaseUrlForAcknowledge).expects().anyNumberOfTimes()
    def rdsAuthRequiredForThisEnv: CallHandler[Boolean] = (() => mockAppConfig.rdsAuthRequiredForThisEnv).expects().anyNumberOfTimes()
    def cipFraudServiceBaseUrl: CallHandler[String]     = (() => mockAppConfig.cipFraudServiceBaseUrl).expects().anyNumberOfTimes()
    def cipFraudToken: CallHandler[String]              = (() => mockAppConfig.cipFraudToken).expects().anyNumberOfTimes()
    def cipFraudUsername: CallHandler[String]           = (() => mockAppConfig.cipFraudUsername).expects().anyNumberOfTimes()
    // RDS Auth
    def rdsSasBaseUrlForAuth: CallHandler[String]      = (() => mockAppConfig.rdsSasBaseUrlForAuth).expects().anyNumberOfTimes()
    def rdsAuthCredential: CallHandler[AuthCredential] = (() => mockAppConfig.rdsAuthCredential).expects().anyNumberOfTimes()

    // API Config
    def featureSwitch: CallHandler[Option[Configuration]]       = (() => mockAppConfig.featureSwitch).expects()
    def apiGatewayContext: CallHandler[String]                  = (() => mockAppConfig.apiGatewayContext: String).expects()
    def apiStatus(status: String): CallHandler[String]          = (mockAppConfig.apiStatus: String => String).expects(status)
    def endpointsEnabled(version: String): CallHandler[Boolean] = (mockAppConfig.endpointsEnabled: String => Boolean).expects(version)

    def confidenceLevelCheckEnabled: CallHandler[ConfidenceLevelConfig] =
      (() => mockAppConfig.confidenceLevelConfig: ConfidenceLevelConfig).expects()

    def confidenceLevelConfig: CallHandler0[ConfidenceLevelConfig] =
      (() => mockAppConfig.confidenceLevelConfig).expects()

    def endpointAllowsSupportingAgents(endpointName: String): CallHandler[Boolean] =
      (mockAppConfig.endpointAllowsSupportingAgents(_: String)).expects(endpointName)

    // NRS config items
    def nrsApiKey: CallHandler[String]                        = (() => mockAppConfig.nrsApiKey).expects().anyNumberOfTimes()
    def appName: CallHandler[String]                          = (() => mockAppConfig.appName).expects().anyNumberOfTimes()
    def nrsBaseUrl: CallHandler[String]                       = (() => mockAppConfig.nrsBaseUrl).expects().anyNumberOfTimes()
    def nrsRetries: CallHandler[List[FiniteDuration]]         = (() => mockAppConfig.nrsRetries).expects().anyNumberOfTimes()
    def nrsFailedBeforeSeconds: CallHandler[Long]             = (() => mockAppConfig.nrsFailedBeforeSeconds).expects().anyNumberOfTimes()
    def nrsInProgressRetryAfter: CallHandler[Duration]        = (() => mockAppConfig.nrsInProgressRetryAfter).expects().anyNumberOfTimes()
    def nrsSchedulerInitialDelay: CallHandler[FiniteDuration] = (() => mockAppConfig.nrsSchedulerInitialDelay).expects().anyNumberOfTimes()
    def nrsSchedulerDelay: CallHandler[FiniteDuration]        = (() => mockAppConfig.nrsSchedulerDelay).expects().anyNumberOfTimes()

    // IFS config items
    def ifsBaseUrl: CallHandler[String]                         = (() => mockAppConfig.ifsBaseUrl).expects().anyNumberOfTimes()
    def ifsToken: CallHandler[String]                           = (() => mockAppConfig.ifsToken).expects().anyNumberOfTimes()
    def ifsEnv: CallHandler[String]                             = (() => mockAppConfig.ifsEnv).expects().anyNumberOfTimes()
    def ifsEnvironmentHeaders: CallHandler[Option[Seq[String]]] = (() => mockAppConfig.ifsEnvironmentHeaders).expects().anyNumberOfTimes()
  }

}
