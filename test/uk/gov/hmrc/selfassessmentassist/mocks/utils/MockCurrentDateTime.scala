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

package uk.gov.hmrc.selfassessmentassist.mocks.utils

import org.scalamock.handlers.CallHandler
import org.scalamock.scalatest.MockFactory
import org.scalatest.TestSuite
import uk.gov.hmrc.selfassessmentassist.utils.CurrentDateTime

import java.time.{Month, OffsetDateTime, ZoneOffset}

trait MockCurrentDateTime extends TestSuite with MockFactory {

  val mockCurrentDateTime: CurrentDateTime = mock[CurrentDateTime]

  object MockCurrentDateTime {

    def getDateTime: CallHandler[OffsetDateTime] = {
      (() => mockCurrentDateTime.getDateTime)
        .expects()
        .anyNumberOfTimes()
        .returns(OffsetDateTime.of(2022, Month.JANUARY.getValue, 1, 12, 0, 0, 0, ZoneOffset.UTC))
    }

    def dateString(currentDatetime: OffsetDateTime): CallHandler[OffsetDateTime] = {
      (mockCurrentDateTime
        .dateString(_: OffsetDateTime))
        .expects(currentDatetime)
    }

  }

}
