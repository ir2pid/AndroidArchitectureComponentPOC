package com.noisyninja.poc;

import com.noisyninja.poc.integration.DetailPresenterTest;
import com.noisyninja.poc.integration.MainPresenterTest;
import com.noisyninja.poc.junit.UnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * test suite
 * Created by sudiptadutta on 01/05/18.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        DetailPresenterTest.class,
        UnitTest.class,
        MainPresenterTest.class
})

public class JunitAndInstrumentationSuite {

}
