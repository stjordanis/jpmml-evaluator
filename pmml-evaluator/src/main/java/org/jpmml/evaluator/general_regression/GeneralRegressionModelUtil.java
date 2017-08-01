/*
 * Copyright (c) 2017 Villu Ruusmann
 *
 * This file is part of JPMML-Evaluator
 *
 * JPMML-Evaluator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JPMML-Evaluator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with JPMML-Evaluator.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jpmml.evaluator.general_regression;

import org.dmg.pmml.general_regression.GeneralRegressionModel;
import org.jpmml.evaluator.EvaluationException;
import org.jpmml.evaluator.Value;

public class GeneralRegressionModelUtil {

	private GeneralRegressionModelUtil(){
	}

	static
	public <V extends Number> Value<V> computeLink(Value<V> value, Double distParameter, Double linkParameter, GeneralRegressionModel.LinkFunction linkFunction){

		switch(linkFunction){
			case CLOGLOG:
				return value.cloglog();
			case IDENTITY:
				return value;
			case LOG:
				return value.exp();
			case LOGC:
				return value.logc();
			case LOGIT:
				return value.logit();
			case LOGLOG:
				return value.loglog();
			case NEGBIN:
				if(distParameter == null){
					throw new EvaluationException();
				}
				return value.negbin(distParameter);
			case ODDSPOWER:
				if(linkParameter == null){
					throw new EvaluationException();
				}
				return value.oddspower(linkParameter);
			case POWER:
				if(linkParameter == null){
					throw new EvaluationException();
				}
				return value.power(linkParameter);
			case PROBIT:
				return value.probit();
			default:
				throw new EvaluationException();
		}
	}

	static
	public <V extends Number> Value<V> computeCumulativeLink(Value<V> value, GeneralRegressionModel.CumulativeLinkFunction cumulativeLinkFunction){

		switch(cumulativeLinkFunction){
			case LOGIT:
				return value.logit();
			case PROBIT:
				return value.probit();
			case CLOGLOG:
				return value.cloglog();
			case LOGLOG:
				return value.loglog();
			case CAUCHIT:
				return value.cauchit();
			default:
				throw new EvaluationException();
		}
	}
}