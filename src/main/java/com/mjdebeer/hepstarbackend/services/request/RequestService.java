package com.mjdebeer.hepstarbackend.services.request;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {

    public Document buildPricedProductRequestDocument(final boolean oneWay,
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
        Element insured = requestParameters.addElement("Insureds").addElement("Insured");
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

        // Travel Information
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

    public Document buildPolicyIssueDocument(final String firstName,
                                             final String surname,
                                             final String dateOfBirth,
                                             final String residency,
                                             final String nationalId,
                                             final String email,
                                             // From initial call
                                             final String productId,
                                             final boolean oneWay,
                                             final String departureDate,
                                             final Optional<String> returnDate,
                                             final String departureCountry,
                                             final String destinationCountry) {
        Document document = DocumentHelper.createDocument();
        Element request = document.addElement("Request");

        // Authentication
        Element authentication = request.addElement("Authentication");
        authentication.addElement("Channel").addText("API");
        authentication.addElement("Session").addText("{{$guid}}");
        authentication.addElement("Username").addText("impdistributor");
        authentication.addElement("Password").addText("FFRyEGGmMJYHA");
        authentication.addElement("Locale").addText("en_GB");
        authentication.addElement("Currency").addText("USD");

        // Policy Request
        Element policyRequest = request.addElement("RequestParameters").addElement("PolicyRequests").addElement("PolicyRequest");

        policyRequest.addElement("DistributerReference").addText("{{$guid}}");
        policyRequest.addElement("ProductID").addText(productId);

        // Insureds
        Element insured = policyRequest.addElement("Insureds").addElement("Insured").addAttribute("ID", "1");
        insured.addElement("Title");
        insured.addElement("Firstname").addText(firstName);
        insured.addElement("Surname").addText(surname);
        insured.addElement("DOB").addText(dateOfBirth);
        insured.addElement("Residency").addText(residency);
        insured.addElement("NationalID").addText(nationalId);
        insured.addElement("TravelInformation").addElement("TravelItemValue").addText("1000");

        // Contact Information
        policyRequest.addElement("ContactInformation").addElement("Email").addText(email);

        // Travel Information
        Element travelInformation = policyRequest.addElement("TravelInformation");
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
