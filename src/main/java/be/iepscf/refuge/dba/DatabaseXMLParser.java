package be.iepscf.refuge.dba;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.w3c.dom.DOMException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import be.iepscf.refuge.persistence.entitybean.*;


public class DatabaseXMLParser extends DefaultHandler {

    DatabaseLoader manager = new DatabaseLoader();

    public static int tot = 0;
    protected Role defaultRole;
    protected Species currentSpecies;
    protected Race currentRace;
    protected Animal currentAnimal;

    public DatabaseXMLParser() {
    }

    public void load() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("dba/data.xml");
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(is, this);
        } catch (DOMException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startDocument() {
        System.out.println("start of document");
    }

    public void endDocument() {
        System.out.println("end of document" + tot);
    }


    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        tot++;
        //System.out.println(String.format("Element(%s, %s, %s)", uri, localName, qName));
        if (qName.equals("role")) {
            startRole(attributes);
        } else if (qName.equals("user")) {
            startUser(attributes);
        } else if (qName.equals("color")) {
            startColor(attributes);
        } else if (qName.equals("species")) {
            startSpecies(attributes);
        } else if (qName.equals("race")) {
            startRace(attributes);
        } else if (qName.equals("animal")) {
            startAnimal(attributes);
        } else if (qName.equals("contact-request")) {
            startContactRequest(attributes);
        } else if (qName.equals("data")) {
        } else {
            System.err.println("unkown tag : " + qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("species")) {
            currentSpecies = null;
        }
        if (qName.equals("race")) {
            currentRace = null;
        }
        if (qName.equals("animal")) {
            currentAnimal = null;
        }
    }







    public Long readLong(Attributes attributes, String qName) {
        String value = attributes.getValue(qName);
        if (value != null) {
            return Long.valueOf(value);
        }
        return null;
    }

    public void startRole(Attributes attributes) {
        Long id = readLong(attributes, "id");
        String name = attributes.getValue("name");
        String description = attributes.getValue("description");

        if (! manager.hasRole(id)) {
            Role role = manager.addRole(id, name, description);
            if (role != null) {
                System.out.println("added role : " + role);
                if (role.getId() == 1) {
                    System.out.println("setting as default role for users");
                    defaultRole = role;
                }
            }
        } else {
            System.out.println("role alreday exists, ignoring");
        }
    }

    public void startUser(Attributes attributes) {
        String firstName = attributes.getValue("firstName");
        String lastName = attributes.getValue("lastName");
        String email = attributes.getValue("email");
        String phone = attributes.getValue("phone");
        String password = attributes.getValue("password");
        String activeStr = attributes.getValue("active");
        if (email == null || email.trim().contentEquals("")) {
            return;
        }
        if (password == null || password.trim().contentEquals("")) {
            password = firstName;
        }
        boolean active = true;
        if (activeStr != null && activeStr.trim().toLowerCase().equals("false")) {
            active = false;
        }
        if (defaultRole == null) {
            System.out.println("no default role available, aborting user insertion");
            return;
        }
        User user = manager.addUser(firstName, lastName, email, phone, password, active, defaultRole);
        if (user != null) {
            System.out.println("added user : " + user);
        }
    }

    public void startColor(Attributes attributes) {
        String name = attributes.getValue("name");
        if (name == null || name.trim().contentEquals("")) {
            return;
        }
        Color color = manager.addColor(name);
        if (color != null) {
            System.out.println("added color : " + color);
        }
    }

    public void startSpecies(Attributes attributes) {
        String name = attributes.getValue("name");
        if (name == null || name.trim().contentEquals("")) {
            return;
        }
        Species species = manager.addSpecies(name);
        if (species != null) {
            System.out.println("added speciesr : " + species);
            currentSpecies = species;
        }
    }

    public void startRace(Attributes attributes) {
        String name = attributes.getValue("name");
        String speciesName = attributes.getValue("species");
        if (name == null || name.trim().contentEquals("")) {
            return;
        }
        if (currentSpecies == null) {
            System.out.println("no current species, aborting race insertion");
            return;
        }
        Race race = manager.addRace(currentSpecies, name);
        if (race != null) {
            System.out.println("added race : " + race);
            currentRace = race;
        }
    }


    public void startAnimal(Attributes attributes) {
        String name = attributes.getValue("name");
        String description = attributes.getValue("desc");
        String birthYearStr = attributes.getValue("birth");
        String sexStr = attributes.getValue("sex");
        String sterilizedStr = attributes.getValue("sterilized");
        String adoptableStr = attributes.getValue("adoptable");
        String colorName = attributes.getValue("color");
        if (name == null || name.trim().contentEquals("")) {
            return;
        }
        char sex = 'm';
        if (sexStr != null && sexStr.toLowerCase().equals("f")) {
            sex = 'f';
        }
        int birthYear = 0;
        if (birthYearStr != null && !birthYearStr.trim().contentEquals("")) {
            try {
                birthYear = Integer.parseInt(birthYearStr);
            } catch (NumberFormatException nfe) {
                birthYear = 0;
            }
        }
        boolean sterilized = false;
        if (sterilizedStr != null && sterilizedStr.trim().toLowerCase().equals("true")) {
            sterilized = true;
        }
        boolean adoptable = true;
        if (adoptableStr != null && adoptableStr.trim().toLowerCase().equals("false")) {
            adoptable = false;
        }

        if (currentSpecies == null) {
            System.out.println("no current species, aborting animal insertion");
            return;
        }
        Race race = currentRace;
        Color color = null;
        if (colorName != null) {
            color = manager.getColor(colorName);
        }
        Animal animal = manager.addAnimal(name, description, birthYear, sex, sterilized, adoptable, currentSpecies, race, color);
        if (animal != null) {
            System.out.println("added animal : " + animal);
            currentAnimal = animal;
        }
    }

    public void startContactRequest(Attributes attributes) {
        String firstName = attributes.getValue("firstName");
        String lastName = attributes.getValue("lastName");
        String email = attributes.getValue("email");
        String phone = attributes.getValue("phone");
        String message = attributes.getValue("message");
        if (message == null || message.trim().contentEquals("")) {
            return;
        }
        if (currentAnimal == null) {
            System.out.println("no current animal, aborting contact request insertion");
            return;
        }
        ContactRequest cr = manager.addContactRequest(firstName, lastName, email, phone, message, currentAnimal);
        if (cr != null) {
            System.out.println("added contact request : " + cr);
        }
    }

}
