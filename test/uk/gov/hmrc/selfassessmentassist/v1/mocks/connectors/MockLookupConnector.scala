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

package uk.gov.hmrc.selfassessmentassist.v1.mocks.connectors

import org.scalamock.scalatest.MockFactory
import org.scalatest.TestSuite
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.selfassessmentassist.api.connectors.MtdIdLookupConnector
import uk.gov.hmrc.selfassessmentassist.api.models.errors.MtdError

import scala.concurrent.{ExecutionContext, Future}

trait MockLookupConnector extends TestSuite with MockFactory {

  val mockLookupConnector: MtdIdLookupConnector = mock[MtdIdLookupConnector]

  object MockLookupConnector {

    def mockMtdIdLookupConnector(mtdItId: String): Unit = {
      (mockLookupConnector
        .getMtdId(_: String)(_: HeaderCarrier, _: ExecutionContext))
        .expects(*, *, *)
        .once()
        .returns(Future.successful(Right(mtdItId)))
    }

    def mockMtdIdLookupConnectorError(mtdError: MtdError): Unit = {
      (mockLookupConnector
        .getMtdId(_: String)(_: HeaderCarrier, _: ExecutionContext))
        .expects(*, *, *)
        .once()
        .returns(Future.successful(Left(mtdError)))
    }

  }

}
