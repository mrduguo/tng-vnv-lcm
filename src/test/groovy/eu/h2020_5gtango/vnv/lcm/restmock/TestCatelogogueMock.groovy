package eu.h2020_5gtango.vnv.lcm.restmock

import eu.h2020_5gtango.vnv.lcm.model.NetworkService
import eu.h2020_5gtango.vnv.lcm.model.PackageMetadata
import eu.h2020_5gtango.vnv.lcm.model.TestSuite
import eu.h2020_5gtango.vnv.lcm.scheduler.SchedulerTest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TestCatelogogueMock {


    @GetMapping('/mock/catalogue/packages/{packageId:.+}')
    PackageMetadata loadPackageMetadata(@PathVariable('packageId') String packageId) {
        if(packageId== SchedulerTest.MULTIPLE_TEST_PLANS_PACKAGE_ID){
            new PackageMetadata(
                    networkServices: [
                            new NetworkService(name: 'multiple_ns_1', vendor: 'vendor', version: 'version'),
                            new NetworkService(name: 'multiple_ns_2', vendor: 'vendor', version: 'version'),
                    ],
                    testSuites: [
                            new TestSuite(name: 'multiple_test_1', version: 'version'),
                            new TestSuite(name: 'multiple_test_2', version: 'version'),
                    ],
            )
        }else{
            new PackageMetadata(
                    networkServices: [new NetworkService(name: 'name', vendor: 'vendor', version: 'version')],
                    testSuites: [new TestSuite(name: 'name', version: 'version')],
            )
        }
    }

    @GetMapping('/mock/catalogue/nss/{nsId}/matched-test-suites')
    List<TestSuite> findTestsApplicableToNs(@PathVariable('nsId') String nsId) {
        if(nsId.startsWith('multiple_')){
            [
                    new TestSuite(name: 'multiple_test_3', version: 'version'),
                    new TestSuite(name: 'multiple_test_4', version: 'version'),
            ]
        }else{
            [new TestSuite(name: 'name', version: 'version')]
        }
    }

    @GetMapping('/mock/catalogue/test-suites/{testSuiteId}/matched-nss')
    List<NetworkService> findNsApplicableToTest(@PathVariable('testSuiteId') String testSuiteId) {
        if(testSuiteId.startsWith('multiple_')){
            [
                    new NetworkService(name: 'multiple_ns_3', vendor: 'vendor', version: 'version'),
                    new NetworkService(name: 'multiple_ns_4', vendor: 'vendor', version: 'version'),
            ]
        }else{
            [new NetworkService(name: 'name', vendor: 'vendor', version: 'version')]
        }
    }

}
