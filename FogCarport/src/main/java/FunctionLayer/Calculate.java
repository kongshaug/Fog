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

    public void caluclatepoles(Carport carport, Material pole, Material bolt, Material disc)
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

    public void caluclatRem(Carport carport, Material rem)
    {
        int depth = carport.getDepth();

        ArrayList<Part> parts = carport.getParts();
        //calculate rem and put in arraylist

        int remmen = depth;

        Part remmene = new Part(rem, remmen, 2, "Remme i sider, sadles ned	i stolper");
        parts.add(remmene);
        carport.setParts(parts);

    }

    public void caluclatFlatRoof(Carport carport, Material spær, Material beslagV, Material beslagH, Material BeslagSkruer,
            Material lægte, Material RoofScrews)
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
        int NumberOfBeslagskruerPackages = ((NumberOfBeslagV + NumberOfBeslagH) * 9)/200;
        
        if (((NumberOfBeslagV + NumberOfBeslagH) * 9) % 200 != 0 || NumberOfBeslagskruerPackages == 0)
        
        {
        NumberOfBeslagskruerPackages ++;
        }

        Part beslagskruer = new Part(BeslagSkruer, 0, NumberOfBeslagskruerPackages, "til fastgørelse af beslag mellem spær og remmene");
        parts.add(beslagskruer);

        //calculate number of lægter that goes acress the spær
        int numberOfLægter = width / 70 + 1;

        if (width % 70 != 0)
        {
            numberOfLægter++;
        }

        Part lægterne = new Part(lægte, depth, numberOfLægter, "lægges på tværs af spær");
        parts.add(lægterne);

        //calculate number of screws used to fit plastmo to lægter
        int NumberOfScrews = numberOfLægter * NumberOfSpær;
        int PackagesOfScres = NumberOfScrews / 200;
        if (NumberOfScrews % 200 != 0)
        {
            PackagesOfScres++;
        }

        Part Screws = new Part(RoofScrews, 0, PackagesOfScres, "til montering af tag på lægter");
        parts.add(Screws);

        carport.getRoof().setParts(parts);
    }

    public void caluclatFlatLastPartsRoof(Carport carport, Material Plastmo, Material Plastmotætning)
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

    public void caluclatSlopeRoof(Carport carport, Material spær, Material lægteBeslag, Material BeslagSkruer,
            Material taglægte, Material beslagRemToTaglægte, Material ScrewForRem, Material Tegl,
            Material Stern, Material MiddleTegl, Material vandbrædt, Material ToplægteHolderen,
            Material MiddleTeglBeslag)
    {
        int depth = carport.getDepth();
        int width = carport.getWidth();
        int slope = carport.getRoof().getSlope();
       
        ArrayList<Part> parts = carport.getRoof().getParts();

        //length of roof from edge to top
        //which one? --- find out in next episode
        double halfRoofrad = (width / 2) / Math.toRadians(slope);

        double halfRoof = (width / 2) / Math.cos(slope);

        //hight of roof
        int hight = (int) (sqrt(halfRoof * halfRoof - width * width));

        //calculate spær 
        int NumberOfSpær = (int) (halfRoof / 30 + 1) * 2;
        if (depth % 30 != 0)
        {
            NumberOfSpær += 2;
        }

        Part spærene = new Part(spær, depth, NumberOfSpær, "spær monteres på taglægtererne");
        parts.add(spærene);

        //calculate parts for taglægter
        //first we find the number of taglægter and then the mesurments of each element of the taglægte
        int numberOfTaglægter = depth / 75 + 1;

        if (depth % 75 != 0)
        {
            numberOfTaglægter++;
        }

        //calculate number of screw to connect spær to taglægter
        int numberOfScrewsForRem = NumberOfSpær * numberOfTaglægter;

        Part ScrewsForRem = new Part(ScrewForRem, 0, numberOfScrewsForRem, "skruer til montering af lægter på taglægter");
        parts.add(ScrewsForRem);

        //the buttom part of the taglægte is the width of the carport
        Part buttomTaglægtePart = new Part(taglægte, width, numberOfTaglægter, "bundstykke til taglægte");
        parts.add(buttomTaglægtePart);

        //the middle part is the hight of the roof (maybe it shoud be minused with the thicknes of the wood)
        Part middleTaglægtePart = new Part(taglægte, hight, numberOfTaglægter, "midterstykke til taglægte");
        parts.add(middleTaglægtePart);

        //the sides of the taglægte is the legth of the roof and there is 2 of them pr. taglægte
        Part sideTaglægtePart = new Part(taglægte, (int) halfRoof, numberOfTaglægter * 2, "siddestykker til taglægte");
        parts.add(sideTaglægtePart);

        //there is 3 of the beslag for the taglægter pr. taglægte
        Part beslagTaglægtePart = new Part(beslagRemToTaglægte, (int) halfRoof, numberOfTaglægter * 4, "beslag til at samle taglægter");
        parts.add(beslagTaglægtePart);

        //screws to mount the taglægte together with the beslag
        int ScrewsForTaglægte = numberOfTaglægter * 36;

        Part beslagscrews = new Part(BeslagSkruer, (int) halfRoof, ScrewsForTaglægte, "beslag til at samle taglægter");
        parts.add(beslagTaglægtePart);

        //beslag to connect the taglægter to rammen
        Part beslagTaglægteToRem = new Part(lægteBeslag, (int) halfRoof, numberOfTaglægter * 2, "beslag til at montere taglægte på rem");
        parts.add(beslagTaglægteToRem);

        //teglsten (30 cm dækbrædte)
        int numberOfTegl = NumberOfSpær * depth / 30;

        Part Teglene = new Part(Tegl, 0, numberOfTegl, "tegl til montering på taget");
        parts.add(Teglene);

        //stern for the middle 5400 mm udhæng
        Part sternbræt = new Part(Stern, depth + 54, 1, "stern til Vindskeder på rejsning");
        parts.add(sternbræt);
        
        Part ToplægteHolder = new Part(ToplægteHolderen, depth + 54, 1, "monteres	på	toppen	af	spæret	(til	toplægte)");
        parts.add(ToplægteHolder);

        //teglsten for the middle top
        int numberOfMiddleTegl = depth / 30;

        if (depth % 30 != 0)
        {
            numberOfMiddleTegl++;
        }

        Part TegleneMiddle = new Part(MiddleTegl, 0, numberOfMiddleTegl, "tegl til montering på midten af taget");
        parts.add(TegleneMiddle);
        
        //beslag for the middleTegl
        
         Part TegleneMiddleBeslagene = new Part(MiddleTeglBeslag, 0, numberOfMiddleTegl, "beslag til montering af midtertegl");
        parts.add(TegleneMiddleBeslagene);

        //screws for the MiddleTegl
        Part TegleneMiddleScrews = new Part(BeslagSkruer, 0, numberOfMiddleTegl * 2, "skruer til tegl monteret på midten af taget");
        parts.add(TegleneMiddleScrews);

        

        //calculate vandbræt they overlap with 3 cm
        int numberOfPlanks = width / 2 / 7;

        if (carport.getShed() == null)
        {
            numberOfPlanks += numberOfPlanks;
        } else
        {
            numberOfPlanks = numberOfPlanks * 3;
        }

        Part vandbrædder = new Part(vandbrædt, hight, numberOfPlanks, "brædder til at bekæde tag for og bag skæres til");
        parts.add(vandbrædder);
        
        carport.getRoof().setParts(parts);

    }

}
