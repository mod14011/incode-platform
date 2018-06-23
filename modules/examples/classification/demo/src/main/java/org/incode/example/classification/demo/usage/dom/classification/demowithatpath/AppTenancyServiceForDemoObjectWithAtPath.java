package org.incode.example.classification.demo.usage.dom.classification.demowithatpath;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;

import org.incode.example.classification.dom.spi.ApplicationTenancyService;
import org.incode.example.classification.demo.shared.demowithatpath.dom.DemoObjectWithAtPath;

@DomainService(nature = NatureOfService.DOMAIN)
public class AppTenancyServiceForDemoObjectWithAtPath implements ApplicationTenancyService {
    @Override
    public String atPathFor(final Object domainObjectToClassify) {
        if (domainObjectToClassify instanceof DemoObjectWithAtPath) {
            return ((DemoObjectWithAtPath) domainObjectToClassify).getAtPath();
        }
        return null;
    }
}