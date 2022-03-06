package com.mjdebeer.hepstarbackend.builder.request;

import lombok.AllArgsConstructor;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentWriter {


    public Document createDocument(final boolean oneWay,
                                   final String departureCountry,
                                   final String countryOfResidency,
                                   final String destinationCountry,
                                   final String departureDate,
                                   final Optional<String> returnDate) {
        Document document = DocumentHelper.createDocument();
        Element request = document.addElement("Request");

        // Stubbing unknown data for demo

        // Authentication
        Element authentication = request.addElement("Authentication");
        authentication.addElement("Channel").addText("API");
        authentication.addElement("Session").addText("{{$guid}}");
        authentication.addElement("Username").addText("impdistributor");
        authentication.addElement("Password").addText("FFRyEGGmMJYHA");
        authentication.addElement("Locale").addText("en_GB");
        authentication.addElement("Currency").addText("USD");

        // Request params
        Element requestParameters = request.addElement("RequestParameters");

        // Insureds
        Element insureds = requestParameters.addElement("Insureds");
        Element insured = insureds.addElement("Insured");
        insured.addAttribute("ID", "1");
        insured.addElement("DOB").addText("1983-09-25");
        insured.addElement("Residency").addText(countryOfResidency);

        // Personalisation
        Element personalisation = insured.addElement("Personalisation");
        personalisation.addElement("PersonalisationItem").addAttribute("Type", "TicketService").addText("no");
        personalisation.addElement("PersonalisationItem").addAttribute("Type", "Frequentflyer").addText("no");
        personalisation.addElement("PersonalisationItem").addAttribute("Type", "Tickettype").addText("standard");
        personalisation.addElement("PersonalisationItem").addAttribute("Type", "Cabinclass").addText("economy");

        // Travel Item Val
        insured.addElement("TravelInformation").addElement("TravelItemValue").addText("1000");
        // Insureds end

        // Travel Information
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Element travelInformation = requestParameters.addElement("TravelInformation");
        travelInformation.addElement("StartDate").addText(departureDate);
        if (!oneWay && returnDate.isPresent()) {
            travelInformation.addElement("EndDate").addText(returnDate.get());
        }
        travelInformation.addElement("DepartureCountry").addText(departureCountry);
        travelInformation.addElement("CoverCountries").addElement("CoverCountry").addText(destinationCountry);
        travelInformation.addElement("BookingValue").addText("2000");

        // Flight Informations
        Element flightInformations = travelInformation.addElement("FlightInformations");

        Element flightInformationOne = flightInformations.addElement("FlightInformation").addAttribute("Segment", "1");
        flightInformationOne.addElement("Airline").addText("EK");
        flightInformationOne.addElement("SupplierReference").addText("309");
        flightInformationOne.addElement("FlightNumber").addText("XYZ");
        flightInformationOne.addElement("StartDate").addText(departureDate);
        flightInformationOne.addElement("EndDate").addText(departureDate);
        flightInformationOne.addElement("CoverCountries").addElement("CoverCountry").addText(destinationCountry);

        if (!oneWay && returnDate.isPresent()) {
            Element flightInformationTwo = flightInformations.addElement("FlightInformation").addAttribute("Segment", "2");
            flightInformationTwo.addElement("Airline").addText("EK");
            flightInformationTwo.addElement("SupplierReference").addText("309");
            flightInformationTwo.addElement("FlightNumber").addText("XYZ");
            flightInformationTwo.addElement("StartDate").addText(returnDate.get());
            flightInformationTwo.addElement("EndDate").addText(returnDate.get());
            flightInformationTwo.addElement("CoverCountries").addElement("CoverCountry").addText(departureCountry);
        }


        return document;
    }

}
