/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import FunctionLayer.HelpingClasses.Carport;
import FunctionLayer.HelpingClasses.Material;
import FunctionLayer.HelpingClasses.Part;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author aamandajuhl
 */
public class CalculateRoof
{

    private CalculatePackages cp;

    public CalculateRoof(CalculatePackages cp)
    {
        this.cp = cp;
    }

    public void calculateFlatRoof(Carport carport, Material spær, Material beslagV, Material beslagH, Material BeslagSkruer,
            Material lægte, Material RoofScrews, Material understern, Material overstern, Material vandbræt, Material skruer)
    {
        int depth = carport.getDepth();
        int width = carport.getWidth();

        ArrayList<Part> parts = carport.getRoof().getParts();
        //calculate spær and put in arraylist
        int NumberOfSpær = depth / 60 + 1;
        if (depth % 60 != 0)
        {
            NumberOfSpær++;
        }

        Part spærene = new Part(spær, width, NumberOfSpær, "Spær monteres på tværs af de 2 remme");
        parts.add(spærene);

        //for each spær there is 2 beslag
        int NumberOfBeslagV = NumberOfSpær;

        Part beslageneV = new Part(beslagV, 0, NumberOfBeslagV, "Til montering af spær på remmene");
        parts.add(beslageneV);

        int NumberOfBeslagH = NumberOfSpær;

        Part beslageneH = new Part(beslagH, 0, NumberOfBeslagH, "Til montering af spær på remmene");
        parts.add(beslageneH);

        //for each beslag you need 9 beslagskruer
        int numberOfBeslagskruer = ((NumberOfBeslagV + NumberOfBeslagH) * 9);
        int NumberOfBeslagskruerPackages = cp.calcPackage250(numberOfBeslagskruer);

        Part beslagskruer = new Part(BeslagSkruer, 0, NumberOfBeslagskruerPackages, "Til fastgørelse af beslag mellem spær og remmene");
        parts.add(beslagskruer);

        //calculate number of lægter that goes across the spær
        int numberOfLægter = width / 70 + 1;

        if (width % 70 != 0)
        {
            numberOfLægter++;
        }

        Part lægterne = new Part(lægte, depth, numberOfLægter, "Lægges på tværs af spærene");
        parts.add(lægterne);

        //calculate number of screws used to fit plastmo to lægter
        int NumberOfScrews = numberOfLægter * NumberOfSpær;
        int PackagesOfScrews = cp.calcPackage200(NumberOfScrews);

        Part Screws = new Part(RoofScrews, 0, PackagesOfScrews, "Til montering af tag på lægter");
        parts.add(Screws);

        calcStern(NumberOfSpær, width, depth, parts, understern, overstern, vandbræt, skruer);

        carport.getRoof().setParts(parts);
    }

    private void calcStern(int numberOfSpær, int width, int depth, ArrayList<Part> parts, Material understern, Material overstern, Material vandbræt, Material Skruer)
    {
        Part understernFront = new Part(understern, width + 5, 1, "Understernbræt til forenden");
        parts.add(understernFront);

        Part understernSide = new Part(understern, depth, 2, "Understernbræt til siderne");
        parts.add(understernSide);

        Part understernBack = new Part(understern, width - 5, 1, "Understernbræt til bagenden");
        parts.add(understernBack);

        Part oversternFront = new Part(overstern, width + 5, 1, "Oversternbræt til forenden");
        parts.add(oversternFront);

        Part oversternSide = new Part(overstern, depth, 2, "Oversternbræt til siderne");
        parts.add(oversternSide);

        Part vandbrætFront = new Part(vandbræt, width + 5, 1, "Vandbræt på stern til forenden");
        parts.add(vandbrætFront);

        Part vandbrætSide = new Part(vandbræt, depth, 2, "Vandbræt på stern til siderne");
        parts.add(vandbrætSide);

        //2 screws pr spær, 4 understern + 3 overstern = 7
        int skruerStern = (numberOfSpær * 2) * 7;

        // 1 screw pr spær, 3 vandbrædder
        int skruerVandbræt = numberOfSpær * 3;

        int skruerPackage = cp.calcPackage200((skruerStern + skruerVandbræt));

        Part skruer = new Part(Skruer, 0, skruerPackage, "Til montering af stern og vandbrædder");
        parts.add(skruer);

    }

    public void calculatePlatsmo(Carport carport, Material Plastmo, Material Plastmotætning)
    {
        int depth = carport.getDepth();
        int width = carport.getWidth();
        ArrayList<Part> parts = carport.getRoof().getParts();

        //on top of the roof is Plastmo Ecolite blåtonet 
        //plastmo is 120 wide and overlap with 20 cm which is why we devide with 100 insted of 120
        int numberOfPlastmo = width / 100;
        if (width % 120 != 0)
        {
            numberOfPlastmo++;
        }

        Part Plastmoen = new Part(Plastmo, depth, numberOfPlastmo, "Tagplader monteres på lægter");
        parts.add(Plastmoen);

        //at the end of the plastmo there is a tætningsprofil
        Part Plastmotætningen = new Part(Plastmotætning, depth, 2, "Monteres i for- og bagside af Plastmo til tætning for regnvand");
        parts.add(Plastmotætningen);

        carport.getRoof().setParts(parts);
    }

    public void calculateSlopeRoof(Carport carport, Material spær, Material taglægte, Material spærBeslag, Material BeslagSkruer, Material screws, Material universalV, Material universalH,
            Material ToplægteHolderen, Material Tegl, Material Rygsten, Material RygstensBeslag, Material Beklædning, Material vandBræt, Material trykImpBræt, Material sternScrews, Material Skrue1, Material Skrue2)
    {
        int depth = carport.getDepth();
        int width = carport.getWidth();
        int slope = carport.getRoof().getSlope();

        ArrayList<Part> parts = carport.getRoof().getParts();

        //length of roof from edge to top
        //which one? --- find out in next episode
        //double angleinradians = slope * Math.PI / 180.0;
      //  double halfRooff = (width / 2) / angleinradians;
        //cos(A)/b=c HHHHEREre
        double cosAngle = Math.cos(Math.toRadians(slope));
        double halfRoofD  = (width / 2)/  cosAngle;
        int halfRoof = (int) halfRoofD;
        

        //hight of roof
        int height = (int) (sqrt(halfRoof * halfRoof -( (width/2) * (width/2))));
    

        //calculate taglægter
        int NumberOfTaglægter = calcTaglægter(halfRoof, depth, taglægte, parts);

        //first we find the number of spær and then the measurments of each element of the spær
        int numberOfSpær = calcSpær(depth, width, height, halfRoof, spær, parts);

        //calculate parts for spær
        calcSpærBeslag(numberOfSpær, parts, spærBeslag, BeslagSkruer, universalH, universalV);

        //calculate number of screw to connect Taglægter on to spær
        int numberOfScrewsForSpær = NumberOfTaglægter * numberOfSpær;
        int PackagesOfScrews = cp.calcPackage100(numberOfScrewsForSpær);

        Part ScrewsForTaglægter = new Part(screws, 0, PackagesOfScrews, "Skruer til montering af taglægter på spær");
        parts.add(ScrewsForTaglægter);

        //calculations for toplægte, tegl and beklædning 
        calcToplægte(depth, parts, numberOfSpær, taglægte, ToplægteHolderen);
        
        if(carport.getRoof().getType().getName().contains("Betontagsten"))
        {
            calcTegl(depth, NumberOfTaglægter, parts, Tegl, Rygsten, RygstensBeslag);
        } else
        {
            calcEternit(slope, depth, width, parts, Tegl, Rygsten, BeslagSkruer);
        }
        
        calcBeklædning(width, height, carport, Beklædning, Skrue1, Skrue2, parts);

        Part vindskeder = new Part(trykImpBræt, (int) halfRoof, 4, "Vindskeder på rejsning");
        parts.add(vindskeder);

        Part vandbrædder = new Part(vandBræt, (int) halfRoof, 4, "Vandbrædder på vindskeder");
        parts.add(vandbrædder);

        Part sternbrædder = new Part(trykImpBræt, depth, 2, "Sternbrædder til siderne af carporten");
        parts.add(sternbrædder);

        int screwsForVindskeder = (NumberOfTaglægter * 2) * 2;
        int screwsForVandbræt = (NumberOfTaglægter * 2) * 2;
        int screwsForStern = (numberOfSpær * 2) * 2;
        int TotalScrews = screwsForVindskeder + screwsForVandbræt + screwsForStern;
        int Packages = cp.calcPackage200(TotalScrews);

        Part sternscrews = new Part(sternScrews, 0, Packages, "Til montering af stern, vindskeder og vandbræt");
        parts.add(sternscrews);

        carport.getRoof().setParts(parts);

    }

    private Integer calcTaglægter(double halfRoof, int depth, Material taglægte, ArrayList<Part> parts)
    {
        int NumberOfTaglægter = (int) (halfRoof / 30 + 1) * 2;
        if (depth % 30 != 0)
        {
            NumberOfTaglægter += 2;
        }
        Part taglægter = new Part(taglægte, depth, NumberOfTaglægter, "Taglægterne monteres på spærene");
        parts.add(taglægter);

        return NumberOfTaglægter;
    }

    private Integer calcSpær(int depth, int width, int height, double halfRoof, Material spær, ArrayList<Part> parts)

    {
        int numberOfSpær = depth / 75 + 1;

        if (depth % 75 != 0)
        {
            numberOfSpær++;
        }

        //the buttom part of each spær is the width of the carport
        Part buttomSpærPart = new Part(spær, width, numberOfSpær, "Bundstykker til spær");
        parts.add(buttomSpærPart);

        //the middle part is the hight of the roof (maybe it shoud be minused with the thicknes of the wood)
        Part middleSpærPart = new Part(spær, height, numberOfSpær, "Midterstykker til spær");
        parts.add(middleSpærPart);

        //the sides of each spær is the length of the roof and there is 2 of them pr. spær
        Part sideSpærPart = new Part(spær, (int) halfRoof, numberOfSpær * 2, "Sidestykker til spær");
        parts.add(sideSpærPart);

        return numberOfSpær;

    }

    private void calcSpærBeslag(int numberOfSpær, ArrayList<Part> parts, Material spærBeslag, Material BeslagSkruer, Material universalH, Material universalV)
    {
        //there is 4 of the beslag for the spær pr. spær
        Part beslagSpærPart = new Part(spærBeslag, 0, numberOfSpær * 4, "Beslag til at samle spær");
        parts.add(beslagSpærPart);

        //beslag to connect the spær to remmen
        Part beslagSpærToRemH = new Part(universalH, 0, numberOfSpær, "Beslag til at montere spærene på remmmen");
        parts.add(beslagSpærToRemH);

        Part beslagSpærToRemV = new Part(universalV, 0, numberOfSpær, "Beslag til at montere spærene på remmen");
        parts.add(beslagSpærToRemV);

        //screws to mount the spær together with the beslag, and for the universalbeslag (3 screws pr. surface * 3 surfaces pr. beslag)
        int ScrewsForSpær = numberOfSpær * 36;
        int ScrewsForUniversal = (numberOfSpær * 2) * 9;
        int ScrewsTotal = ScrewsForSpær + ScrewsForUniversal;
        int PackagesOfScrews = cp.calcPackage250(ScrewsTotal);

        Part beslagscrews = new Part(BeslagSkruer, 0, PackagesOfScrews, "Skruer til at samle spær og montering af universalbeslag");
        parts.add(beslagscrews);
    }

    private void calcToplægte(int depth, ArrayList<Part> parts, int numberOfSpær, Material taglægte, Material ToplægteHolderen)
    {
        Part ToplægteHolder = new Part(ToplægteHolderen, 0, numberOfSpær, "Monteres på toppen af spærerne (til toplægte)");
        parts.add(ToplægteHolder);

        //toplægte for the middle
        Part toplægte = new Part(taglægte, depth, 1, "Toplægte til	montering af rygsten");
        parts.add(toplægte);

    }

    private void calcTegl(int depth, int NumberOfTaglægter, ArrayList<Part> parts, Material Tegl, Material Rygsten, Material RygstensBeslag)
    {
        //teglsten (30 cm dækbredde)
        int numberOfTegl = NumberOfTaglægter * depth / 30;

        Part Teglene = new Part(Tegl, 0, numberOfTegl, "Tegl til montering på taget");
        parts.add(Teglene);

        //Rygsten for the top
        int numberOfRygsten = depth / 30;

        if (depth % 30 != 0)
        {
            numberOfRygsten++;
        }

        Part rygsten = new Part(Rygsten, 0, numberOfRygsten, "Rygsten til montering på toplægten");
        parts.add(rygsten);

        //beslag for rygsten
        Part rygstensBeslag = new Part(RygstensBeslag, 0, numberOfRygsten, "Beslag til montering af rygsten");
        parts.add(rygstensBeslag);

    }

    private void calcEternit(int slope, int depth, int width, ArrayList<Part> parts, 
                            Material Eternit, Material Vinkelrygning, Material BeslagSkruer)
    {
        //plates mesurments 110 x 57 cm with 20 cm overlay they become 90 cm x 37 cm
        int numberOfEternit = (width / 37) * (depth / 90);

        Part Eterniten = new Part(Eternit, 0, numberOfEternit, "Eternit til montering på taget");
        parts.add(Eterniten);

        //eternit vinkel rygning (45 cm), 7,5 cm overlay on each side 
        int numberOfVinkelrygning = depth / 30;

        if (depth % 30 != 0)
        {
            numberOfVinkelrygning++;
        }

        Part vinkelrygning = new Part(Vinkelrygning, 0, numberOfVinkelrygning, "Vinkelrygning til montering på toplægten med en vinkel på: " + slope);
        parts.add(vinkelrygning);
        
        //screws for plates 4 pr. vinkelrygning or plate
        int numberOfScrews = ((numberOfEternit  + numberOfVinkelrygning) * 4);
        int packages = cp.calcPackage250(numberOfScrews);

        Part rygstensskruer = new Part(BeslagSkruer, 0, packages, "Skruer til montering af vinkelrygning og eternitplader");
        parts.add(rygstensskruer);

    }
    private void calcBeklædning(int width, int height, Carport carport, Material Beklædning, Material Skrue1, Material Skrue2, ArrayList<Part> parts)
    {
        //calculate beklædning they overlap with 3 cm
        int numberOfPlanks = width / 2 / 7;

        if (carport.getShed() == null)
        {
            numberOfPlanks += numberOfPlanks;
        } else
        {
            numberOfPlanks = numberOfPlanks * 3;
        }

        Part beklædning = new Part(Beklædning, height, numberOfPlanks, "Brædder til beklædning af gavl for og bag (skal skæres til)");
        parts.add(beklædning);

        int skruerYderbræt = (numberOfPlanks / 2) * 6;
        int packagesYderbræt = cp.calcPackage200(skruerYderbræt);

        Part yderbrætSkruer = new Part(Skrue1, 0, packagesYderbræt, "Til montering af yderbræt, ved beklædning af gavl");
        parts.add(yderbrætSkruer);

        int skruerInderbræt = (numberOfPlanks / 2) * 3;
        int packagesInderbræt = cp.calcPackage350(skruerInderbræt);

        Part inderbrætSkruer = new Part(Skrue2, 0, packagesInderbræt, "Til montering af inderbræt, ved beklædning af gavl");
        parts.add(inderbrætSkruer);

    }
}
