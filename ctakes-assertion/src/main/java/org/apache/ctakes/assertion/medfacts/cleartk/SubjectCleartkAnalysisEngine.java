/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ctakes.assertion.medfacts.cleartk;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.cleartk.classifier.Instance;

import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;

public class SubjectCleartkAnalysisEngine extends
		AssertionCleartkAnalysisEngine {

	@Override
	public void setClassLabel(IdentifiedAnnotation entityMention,
			Instance<String> instance) throws AnalysisEngineProcessException {
		if (this.isTraining())
	      {
	        String subj = entityMention.getSubject();
	        instance.setOutcome(subj);
	        this.dataWriter.write(instance);
	      } else
	      {
	        String label = this.classifier.classify(instance.getFeatures());
	        entityMention.setSubject(label);
	      }
	}

}