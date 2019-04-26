/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author benja
 */
public class Calculate
{

    public void calculatepoles(Carport carport, Material pole, Material bolt, Material disc)
    {
        int depth = carport.getDepth();

        ArrayList<Part> parts = carport.getParts();
        //a pole is placed for each 2.5 meter on the right and left side of the carport
        int numberOfPoles = (depth / 200 * 2) + 2;
        int boltParts = numberOfPoles * 4;

        Part poles = new Part(pole, 300, numberOfPoles, "Stolper nedgraves 90 cm. i jord");
        Part bolts = new Part(bolt, 0, boltParts, "Til montering af rem	på stolper");
        Part discs = new Part(disc, 0, boltParts, "Til montering af rem	på stolper");
        parts.add(poles);
        parts.add(bolts);
        parts.add(discs);
        carport.setParts(parts);

    }

    public void calculateRem(Carport carport, Material rem)
    {
        int depth = carport.getDepth();

        ArrayList<Part> parts = carport.getParts();
        //calculate rem and put in arraylist

        int remmen = depth;

        Part remmene = new Part(rem, remmen, 2, "Remme i sider, sadles ned	i stolper");
        parts.add(remmene);
        carport.setParts(parts);

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

        Part spærene = new Part(spær, width, NumberOfSpær, "spær monteres på tværs af de 2 remme");
        parts.add(spærene);

        //for each spær there is 2 beslag
        int NumberOfBeslagV = NumberOfSpær;

        Part beslageneV = new Part(beslagV, 0, NumberOfBeslagV, "til montering af spær på remmene");
        parts.add(beslageneV);

        int NumberOfBeslagH = NumberOfSpær;

        Part beslageneH = new Part(beslagH, 0, NumberOfBeslagH, "til montering af spær på remmene");
        parts.add(beslageneH);

        //for each beslag you need 9 beslagskruer
        int NumberOfBeslagskruerPackages = ((NumberOfBeslagV + NumberOfBeslagH) * 9) / 250;

        if (((NumberOfBeslagV + NumberOfBeslagH) * 9) % 250 != 0 || NumberOfBeslagskruerPackages == 0)

        {
            NumberOfBeslagskruerPackages++;
        }

        Part beslagskruer = new Part(BeslagSkruer, 0, NumberOfBeslagskruerPackages, "til fastgørelse af beslag mellem spær og remmene");
        parts.add(beslagskruer);

        //calculate number of lægter that goes across the spær
        int numberOfLægter = width / 70 + 1;

        if (width % 70 != 0)
        {
            numberOfLægter++;
        }

        Part lægterne = new Part(lægte, depth, numberOfLægter, "lægges på tværs af spær");
        parts.add(lægterne);

        //calculate number of screws used to fit plastmo to lægter
        int NumberOfScrews = numberOfLægter * NumberOfSpær;
        int PackagesOfScrews = NumberOfScrews / 200;
        if (NumberOfScrews % 200 != 0)
        {
            PackagesOfScrews++;
        }

        Part Screws = new Part(RoofScrews, 0, PackagesOfScrews, "til montering af tag på lægter");
        parts.add(Screws);
        
        calcStern(carport, NumberOfSpær, width, depth, parts, understern, overstern, vandbræt, skruer);

        carport.getRoof().setParts(parts);
    }
    
    private void calcStern(Carport carport, int numberOfSpær, int width, int depth, ArrayList<Part> parts, Material understern, Material overstern, Material vandbræt, Material Skruer)
    {
        Part understernFront = new Part(understern, width + 5, 1, "understernbræt til forenden");
        parts.add(understernFront);
        
        Part understernSide = new Part(understern, depth, 2, "understernbræt til siderne");
        parts.add(understernSide);
        
        Part understernBack = new Part(understern, width - 5, 1, "understernbræt til bagenden");
        parts.add(understernBack);
        
        Part oversternFront = new Part(overstern, width + 5, 1, "oversternbræt til forenden");
        parts.add(oversternFront);
        
        Part oversternSide = new Part(overstern, depth, 2, "oversternbræt til siderne");
        parts.add(oversternSide);
        
        Part vandbrætFront = new Part(vandbræt, width + 5, 1, "vandbræt på stern til forenden");
        parts.add(vandbrætFront);
        
        Part vandbrætSide = new Part(vandbræt, depth, 2, "vandbræt på stern til siderne");
        parts.add(vandbrætSide);
        
        //2 screws pr spær, 4 understern + 3 overstern = 7
        int skruerStern = (numberOfSpær * 2) * 7;
        
        // 1 screw pr spær, 3 vandbrædder
        int skruerVandbræt = numberOfSpær * 3;
    
        int skruerPackage = (skruerStern + skruerVandbræt) / 200;
        if ((skruerStern + skruerVandbræt) % 200 != 0)
        {
            skruerPackage++;
        }
        
        Part skruer = new Part(Skruer, 0, skruerPackage, "til montering af stern og vandbrædder");
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

        Part Plastmoen = new Part(Plastmo, depth, numberOfPlastmo, "tagplader monteres på lægter");
        parts.add(Plastmoen);

        //at the end of the plastmo there is a tætningsprofil
        Part Plastmotætningen = new Part(Plastmotætning, depth, 2, "monteres i for og bagside af Plastmo til tætning for regnvand");
        parts.add(Plastmotætningen);

        carport.getRoof().setParts(parts);
    }

    public void calculateSlopeRoof(Carport carport, Material spær, Material taglægte, Material spærBeslag, Material BeslagSkruer, Material screws, Material universalV, Material universalH, 
              Material ToplægteHolderen,  Material Tegl, Material Rygsten, Material RygstensBeslag, Material Beklædning, Material vandBræt, Material trykImpBræt, Material sternScrews)
    {
        int depth = carport.getDepth();
        int width = carport.getWidth();
        int slope = carport.getRoof().getSlope();

        ArrayList<Part> parts = carport.getRoof().getParts();

        //length of roof from edge to top
        //which one? --- find out in next episode
        double halfRoof = (width / 2) / Math.cos(slope);

        //hight of roof
        int height = (int) (sqrt(halfRoof * halfRoof - width * width));

        //calculate taglægter
        int NumberOfTaglægter = calcTaglægter(halfRoof, depth, taglægte, parts);

        //first we find the number of spær and then the measurments of each element of the spær
        int numberOfSpær = calcSpær(depth, width, height, halfRoof, spær, parts);

        //calculate parts for spær
        calcSpærBeslag(numberOfSpær, parts, spærBeslag, BeslagSkruer, universalH, universalV);

        //calculate number of screw to connect Taglægter on to spær
        int numberOfScrewsForSpær = NumberOfTaglægter * numberOfSpær;
        int PackagesOfScrews = numberOfScrewsForSpær/100;
        if (numberOfScrewsForSpær % 100 != 0)
        {
            PackagesOfScrews++;
        }
        Part ScrewsForTaglægter = new Part(screws, 0, PackagesOfScrews, "skruer til montering af taglægter på spær");
        parts.add(ScrewsForTaglægter);

        //calculations for toplægte, tegl and beklædning 
        calcToplægte(depth, parts, taglægte, ToplægteHolderen);
        calcTegl(depth, NumberOfTaglægter, parts, Tegl, Rygsten, RygstensBeslag, BeslagSkruer);
        calcBeklædning(width, height, carport, Beklædning, parts);

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
        int Packages = TotalScrews / 200;
        if (Packages % 200 != 0)
        {
            Packages++;
        }
        Part sternscrews = new Part(sternScrews,0, Packages, "til montering af stern, vindskeder og vandbræt");
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
        Part taglægter = new Part(taglægte, depth, NumberOfTaglægter, "taglægterne monteres på spærene");
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
        Part buttomSpærPart = new Part(spær, width, numberOfSpær, "bundstykke til spær");
        parts.add(buttomSpærPart);

        //the middle part is the hight of the roof (maybe it shoud be minused with the thicknes of the wood)
        Part middleSpærPart = new Part(spær, height, numberOfSpær, "midterstykke til spær");
        parts.add(middleSpærPart);

        //the sides of each spær is the length of the roof and there is 2 of them pr. spær
        Part sideSpærPart = new Part(spær, (int) halfRoof, numberOfSpær * 2, "siddestykker til spær");
        parts.add(sideSpærPart);

        return numberOfSpær;

    }

    private void calcSpærBeslag(int numberOfSpær, ArrayList<Part> parts, Material spærBeslag, Material BeslagSkruer, Material universalH, Material universalV)
    {
        //there is 4 of the beslag for the spær pr. spær
        Part beslagSpærPart = new Part(spærBeslag,0, numberOfSpær * 4, "beslag til at samle spær");
        parts.add(beslagSpærPart);
        
        //beslag to connect the spær to remmen
        Part beslagSpærToRemH = new Part(universalH,0, numberOfSpær, "beslag til at montere spærene på remmmen");
        parts.add(beslagSpærToRemH);

        Part beslagSpærToRemV = new Part(universalV,0, numberOfSpær, "beslag til at montere taglægte på rem");
        parts.add(beslagSpærToRemV);

        //screws to mount the spær together with the beslag, and for the universalbeslag (3 screws pr. surface * 3 surfaces pr. beslag)
        int ScrewsForSpær = numberOfSpær * 36;
        int ScrewsForUniversal = (numberOfSpær * 2) * 9;
        int ScrewsTotal = ScrewsForSpær + ScrewsForUniversal;
        int PackagesOfScrews = ScrewsTotal/250;
        if (ScrewsTotal % 250 != 0)
        {
            PackagesOfScrews++;
        }
        Part beslagscrews = new Part(BeslagSkruer,0, PackagesOfScrews, "beslag til at samle spær og motering af universalbeslag");
        parts.add(beslagscrews);
    }

    private void calcToplægte(int depth, ArrayList<Part> parts, Material taglægte, Material ToplægteHolderen)
    {
        Part ToplægteHolder = new Part(ToplægteHolderen, depth + 54, 1, "monteres på toppen af spæret (til toplægte)");
        parts.add(ToplægteHolder);

        //toplægte for the middle 5400 mm udhæng
        Part toplægte = new Part(taglægte, depth + 54, 1, "toplægte til	montering af rygsten");
        parts.add(toplægte);

    }

    private void calcTegl(int depth, int NumberOfTaglægter, ArrayList<Part> parts, Material Tegl, Material Rygsten, Material RygstensBeslag, Material BeslagSkruer)
    {
        //teglsten (30 cm dækbredde)
        int numberOfTegl = NumberOfTaglægter * depth / 30;

        Part Teglene = new Part(Tegl, 0, numberOfTegl, "tegl til montering på taget");
        parts.add(Teglene);

        //Rygsten for the top
        int numberOfRygsten = depth / 30;

        if (depth % 30 != 0)
        {
            numberOfRygsten++;
        }

        Part rygsten = new Part(Rygsten, 0, numberOfRygsten, "tegl til montering på midten af taget");
        parts.add(rygsten);

        //beslag for rygsten
        Part rygstensBeslag = new Part(RygstensBeslag, 0, numberOfRygsten, "beslag til montering af midtertegl");
        parts.add(rygstensBeslag);

//        //screws for the rygsten
//        Part rygstensScrews = new Part(BeslagSkruer, 0, numberOfRygsten * 2, "skruer til montering af rygsten");
//        parts.add(rygstensScrews);
    }

    private void calcBeklædning(int width, int height, Carport carport, Material Beklædning, ArrayList<Part> parts)
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

        Part beklædning = new Part(Beklædning, height, numberOfPlanks, "brædder til at beklæde tag for og bag skæres til");
        parts.add(beklædning);
    }

}
