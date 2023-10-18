@XmlSchema(
    namespace = "http://example.com/customers",
    elementFormDefault = XmlNsForm.QUALIFIED,
    xmlns = {
        @XmlNs(namespaceURI = "http://example.com/customers", prefix = "xsi")
    }
)
package com.example.customer;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;