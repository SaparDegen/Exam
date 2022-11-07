package peaksoft;

import peaksoft.enums.Gender;
import peaksoft.model.*;
import peaksoft.service.service.*;
import peaksoft.service.serviceImpl.*;

import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner scannerN = new Scanner(System.in);
    static Scanner scannerS = new Scanner(System.in);
    static TableService tableService = new TableServiceImpl();
    static PersonService personService = new PersonServiceImpl();
    static CompanyService companyService = new CompanyServiceImpl();
    static SocialMediaService socialMediaService = new SocialMediaServiceImpl();
    static CarService carService = new CarServiceImpl();
    static GarageService garageService = new GarageServiceImpl();

    public static void main( String[] args ){

        while (true) {
            commands();
            int a = getButton();
            int b;
            if (a == 1) {
                tableService.createTable();
            } else if (a == 2) {
                chooseEntity("Save to");
                b = getButton();
                if (b == 1) {
                    companySaveApp();
                } else if (b == 2) {
                    personSaveApp();
                } else if (b == 3) {
                    socialMediaSaveApp();
                } else if (b == 4) {
                    carSaveApp();
                } else if (b == 5) {
                    garageSaveApp();
                }
            } else if (a == 3) {
                chooseEntity("Update to");
                b = getButton();
                if (b == 1) {
                    companyUpdateApp();
                } else if (b == 2) {
                    personUpdateApp();
                } else if (b == 3) {
                    socialMediaUpdateApp();
                } else if (b == 4) {
                    carUpdateApp();
                } else if (b == 5) {
                    garageUpdateApp();
                }
            } else if (a == 4) {
                chooseEntity("Get by Id from");
                b = getButton();
                if (b == 1) {
                    companyGetApp();
                } else if (b == 2) {
                    personGetApp();
                } else if (b == 3) {
                    socialMediaGetApp();
                } else if (b == 4) {
                    carGetApp();
                } else if (b == 5) {
                    garageGetApp();
                }
            } else if (a == 5) {
                chooseEntity("Select all from");
                b = getButton();
                if (b == 1) {
                    companyGetAllApp();
                } else if (b == 2) {
                    personGetAllApp();
                } else if (b == 3) {
                    socialMediaGetAllApp();
                } else if (b == 4) {
                    carGetAllApp();
                } else if (b == 5) {
                    garageGetAllApp();
                }
            } else if (a == 6) {
                chooseEntity("Delete by Id from");
                b = getButton();
                if (b == 1) {
                    companyDeleteApp();
                } else if (b == 2) {
                    personDeleteApp();
                } else if (b == 3) {
                    socialMediaDeleteApp();
                } else if (b == 4) {
                    carDeleteApp();
                } else if (b == 5) {
                    garageDeleteApp();
                }
            } else if (a == 7) {
                assignPersonToSocialMediaApp();
            } else if (a == 8) {
                tableService.dropTable();
            } else if (a == 0) {
                break;
            }
        }
    }

    public static void commands() {
        System.out.println("--------------Commands------------------");
        System.out.println("Press 1 to create");
        System.out.println("Press 2 to save");
        System.out.println("Press 3 to update");
        System.out.println("Press 4 to get by Id");
        System.out.println("Press 5 to get all");
        System.out.println("Press 6 to delete by Id");
        System.out.println("Press 7 to assign Person to SocialMedia");
        System.out.println("Press 8 to drop");
        System.out.println("Press 0 to exit the App");
        System.out.println("----------------------------------------");
    }

    public static int getButton() {
        System.out.print("Input number: ");
        int num = scannerN.nextInt();
        return num;
    }

    public static void chooseEntity(String text) {
        System.out.println(text + " Entity: 1-Company, 2-Person, 3-SocialMedia, 4-Car, 5-Garage");
    }

    public static void personSaveApp() {
        System.out.print("Input name: ");
        String name = scannerS.nextLine();
        System.out.print("Input age: ");
        Integer age = scannerN.nextInt();
        System.out.print("Input gender (M or F): ");
        String genderStr = scannerS.nextLine();
        Gender gender = null;
        if (genderStr.equals("M")) {
            gender = Gender.MALE;
        } else if (genderStr.equals("F")) {
            gender = Gender.FEMALE;
        } else {
            System.out.println("Wrong gender");
        }
        System.out.print("Input Company id: ");
        long companyId = scannerN.nextLong();
        System.out.print("Input SocialMedia id: ");
        long socialMediaId = scannerN.nextLong();
        System.out.print("Input Car id: ");
        long carId = scannerN.nextLong();
        Person person = new Person(name, age, name + "@gmail.com", gender);
        personService.savePerson(person, companyId, socialMediaId, carId);
    }

    public static void personUpdateApp() {
        System.out.print("Input Id: ");
        long personId = scannerN.nextLong();
        System.out.print("Input Person name: ");
        String personName = scannerS.nextLine();
        System.out.print("Input age: ");
        Integer personAge = scannerN.nextInt();
        System.out.print("Input gender (M or F): ");
        String genderStr = scannerS.nextLine();
        Gender gender = null;
        if (genderStr.equals("M")) {
            gender = Gender.MALE;
        } else if (genderStr.equals("F")) {
            gender = Gender.FEMALE;
        } else {
            System.out.println("Wrong gender");
        }
        System.out.print("Input Company id: ");
        long companyId = scannerN.nextLong();
        System.out.print("Input SocialMedia id: ");
        long socialMediaId = scannerN.nextLong();
        System.out.print("Input Car id: ");
        long carId = scannerN.nextLong();
        Person person1 = new Person(personId, personName, personAge, personName + "@gmail.com", gender);
        personService.updatePerson(person1, companyId, socialMediaId, carId);
    }

    public static void personGetApp() {
        System.out.print("Input Id: ");
        long personId = scannerN.nextLong();
        System.out.println(personService.getById(personId));
    }

    public static void personDeleteApp() {
        System.out.print("Input Id: ");
        long personId = scannerN.nextLong();
        personService.deleteById(personId);
    }

    public static void personGetAllApp() {
        personService.getAll().forEach(System.out::println);
    }

    public static void assignPersonToSocialMediaApp() {
        System.out.print("Input personId: ");
        long personId = scannerN.nextLong();
        System.out.print("Input socialMediaId: ");
        long socialMediaId = scannerN.nextLong();
        personService.assignPersonToSocialMedia(personId, socialMediaId);
    }

    public static void companySaveApp() {
        System.out.print("Input Company name: ");
        String companyName = scannerS.nextLine();
        System.out.print("Input location: ");
        String location = scannerS.nextLine();
        Company company = new Company(companyName, location);
        companyService.saveCompany(company);
    }

    public static void companyUpdateApp() {
        System.out.print("Input Company Id: ");
        long companyId = scannerN.nextLong();
        System.out.print("Input Company's name: ");
        String companyName = scannerS.nextLine();
        System.out.print("Input location: ");
        String location = scannerS.nextLine();
        Company company = new Company(companyId, companyName, location);
        companyService.updateCompany(company);
    }

    public static void companyGetApp() {
        System.out.print("Input Id: ");
        long companyId = scannerN.nextLong();
        System.out.println(companyService.getById(companyId));
    }

    public static void companyDeleteApp() {
        System.out.print("Input Id: ");
        long companyId = scannerN.nextLong();
        companyService.deleteById(companyId);
    }

    public static void companyGetAllApp() {
        companyService.getAll().forEach(System.out::println);
    }

    public static void socialMediaSaveApp() {
        System.out.print("Input SocialMedia's name: ");
        String mediaName = scannerS.nextLine();
        Socialmedia socialmedia = new Socialmedia(mediaName);
        socialMediaService.saveSocialMedia(socialmedia);
    }

    public static void socialMediaUpdateApp() {
        System.out.print("Input Id: ");
        long mediaId = scannerN.nextLong();
        System.out.print("Input SocialMedia name: ");
        String mediaName = scannerS.nextLine();
        Socialmedia socialmedia = new Socialmedia(mediaId, mediaName);
        socialMediaService.updateSocialMedia(socialmedia);
    }

    public static void socialMediaGetApp() {
        System.out.print("Input Id: ");
        long mediaId = scannerN.nextLong();
        System.out.println(socialMediaService.getById(mediaId));
    }

    public static void socialMediaDeleteApp() {
        System.out.print("Input Id: ");
        long mediaId = scannerN.nextLong();
        socialMediaService.deleteById(mediaId);
    }

    public static void socialMediaGetAllApp() {
        socialMediaService.getAll().forEach(System.out::println);
    }

    public static void carSaveApp() {
        System.out.print("Input Car's mark: ");
        String mark = scannerS.nextLine();
        System.out.print("Input produced country: ");
        String country = scannerS.nextLine();
        System.out.print("Input GarageId: ");
        long garageId = scannerN.nextLong();
        Car newCar = new Car(mark, country);
        carService.saveCar(newCar, garageId);
    }

    public static void carUpdateApp() {
        System.out.print("Input Id: ");
        long carId = scannerN.nextLong();
        System.out.print("Input Car's mark: ");
        String mark = scannerS.nextLine();
        System.out.print("Input produced country: ");
        String country = scannerS.nextLine();
        System.out.print("Input Garage Id: ");
        long garageId = scannerN.nextLong();
        Car car1 = new Car(carId, mark, country);
        carService.updateCar(car1, garageId);
    }

    public static void carGetApp() {
        System.out.print("Input Id: ");
        long carId = scannerN.nextLong();
        System.out.println(carService.getById(carId));
    }

    public static void carDeleteApp() {
        System.out.print("Input Id: ");
        long carId = scannerN.nextLong();
        carService.deleteById(carId);
    }

    public static void carGetAllApp() {
        carService.getAll().forEach(System.out::println);
    }

    public static void garageSaveApp() {
        System.out.print("Input Garage name: ");
        String name = scannerS.nextLine();
        Garage garage = new Garage(name);
        garageService.saveGarage(garage);
    }

    public static void garageUpdateApp() {
        System.out.print("Input Id: ");
        long garageId = scannerN.nextLong();
        System.out.print("Input Garage name: ");
        String garageName = scannerS.nextLine();
        Garage garage = new Garage(garageId, garageName);
        garageService.updateGarage(garage);
    }

    public static void garageGetApp() {
        System.out.print("Input Id: ");
        long garageId = scannerN.nextLong();
        System.out.println(garageService.getById(garageId));
    }

    public static void garageDeleteApp() {
        System.out.print("Input Id: ");
        long garageId = scannerN.nextLong();
        garageService.deleteById(garageId);
    }

    public static void garageGetAllApp() {
        garageService.getAll().forEach(System.out::println);
    }
}