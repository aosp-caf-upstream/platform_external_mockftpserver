/*
 * Copyright 2008 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.narc.results

/**
 * Represents the top-level results for from an analysis across one or more source directories
 *
 * @author Chris Mair
 * @version $Revision: 182 $ - $Date: 2008-11-30 21:37:49 -0500 (Sun, 30 Nov 2008) $
 */
class ReportResults implements CompositeResults {

    // This is a Map of sourceDirectory -> Results
    private Map resultsMap = new LinkedHashMap()

    /**
     * Add the specified source directory and its associated results
     * @param sourceDirectory - the source directory to add
     * @param results - the Results associated with the source directory
     */
    void addResults(String sourceDirectory, Results results) {
        resultsMap[sourceDirectory] = results
    }

    /**
     * Return the List of source directories
     */
    List getSourceDirectories() {
        return resultsMap.keySet() as List
    }

    List getViolationsWithPriority(int priority) {
        def violations = []
        resultsMap.values().each { results ->
            violations.addAll(results.getViolationsWithPriority(priority))
        }
        return violations
    }

    int getTotalNumberOfFiles() {
        def count = 0
        resultsMap.values().each { results ->
            count += results.totalNumberOfFiles
        }
        return count
    }

    int getNumberOfFilesWithViolations() {
        def count = 0
        resultsMap.values().each { results ->
            count += results.numberOfFilesWithViolations
        }
        return count
    }

}