import java.util.*;
/**
 * Created by fantonio on 13/04/16.
 */
public class Main {
    public static void main(String [] args) {
        Scanner kbd = new Scanner(System.in);
        try{
        while(kbd.hasNext()){
            table(kbd.nextInt());
            kbd.nextLine();
        }
        }catch (NoSuchElementException nse){}
        finally {
            kbd.close();
        }
    }

    public static void table(int row){
        long [][] mat = {{0L, 0L, 0L, 0L, 0L, 0L},
                {1L, 0L, 1L, 0L, 1L, 0L},
                {5L, 4L, 9L, 18L, 17L, 64L},
                {14L, 22L, 36L, 180L, 98L, 1198L},
                {30L, 70L, 100L, 900L, 354L, 9646L},
                {55L, 170L, 225L, 3150L, 979L, 49646L},
                {91L, 350L, 441L, 8820L, 2275L, 192206L},
                {140L, 644L, 784L, 21168L, 4676L, 609980L},
                {204L, 1092L, 1296L, 45360L, 8772L, 1670844L},
                {285L, 1740L, 2025L, 89100L, 15333L, 4085292L},
                {385L, 2640L, 3025L, 163350L, 25333L, 9125292L},
                {506L, 3850L, 4356L, 283140L, 39974L, 18934762L},
                {650L, 5434L, 6084L, 468468L, 60710L, 36954346L},
                {819L, 7462L, 8281L, 745290L, 89271L, 68485690L},
                {1015L, 10010L, 11025L, 1146600L, 127687L, 121422938L},
                {1240L, 13160L, 14400L, 1713600L, 178312L, 207181688L},
                {1496L, 17000L, 18496L, 2496960L, 243848L, 341858168L},
                {1785L, 21624L, 23409L, 3558168L, 327369L, 547653912L},
                {2109L, 27132L, 29241L, 4970970L, 432345L, 854603736L},
                {2470L, 33630L, 36100L, 6822900L, 562666L, 1302647334L},
                {2870L, 41230L, 44100L, 9216900L, 722666L, 1944087334L},
                {3311L, 50050L, 53361L, 12273030L, 917147L, 2846479174L},
                {3795L, 60214L, 64009L, 16130268L, 1151403L, 4096000678L},
                {4324L, 71852L, 76176L, 20948400L, 1431244L, 5801351732L},
                {4900L, 85100L, 90000L, 26910000L, 1763020L, 8098236980L},
                {5525L, 100100L, 105625L, 34222500L, 2153645L, 11154486980L},
                {6201L, 117000L, 123201L, 43120350L, 2610621L, 15175875780L},
                {6930L, 135954L, 142884L, 53867268L, 3142062L, 20412695394L},
                {7714L, 157122L, 164836L, 66758580L, 3756718L, 27167150178L},
                {8555L, 180670L, 189225L, 82123650L, 4463999L, 35801636626L},
                {9455L, 206770L, 216225L, 100328400L, 5273999L, 46747976626L},
                {10416L, 235600L, 246016L, 121777920L, 6197520L, 60517674736L},
                {11440L, 267344L, 278784L, 146919168L, 7246096L, 77713272560L},
                {12529L, 302192L, 314721L, 176243760L, 8432017L, 99040875824L},
                {13685L, 340340L, 354025L, 210290850L, 9768353L, 125323932272L},
                {14910L, 381990L, 396900L, 249650100L, 11268978L, 157518341022L},
                {16206L, 427350L, 443556L, 294964740L, 12948594L, 196728976542L},
                {17575L, 476634L, 494209L, 346934718L, 14822755L, 244227712926L},
                {19019L, 530062L, 549081L, 406319940L, 16907891L, 301473036670L},
                {20540L, 587860L, 608400L, 473943600L, 19221332L, 370131338668L},
                {22140L, 650260L, 672400L, 550695600L, 21781332L, 452099978668L},
                {23821L, 717500L, 741321L, 637536060L, 24607093L, 549532217948L},
                {25585L, 789824L, 815409L, 735498918L, 27718789L, 664864118492L},
                {27434L, 867482L, 894916L, 845695620L, 31137590L, 800843509466L},
                {29370L, 950730L, 980100L, 969318900L, 34885686L, 960561124314L},
                {31395L, 1039830L, 1071225L, 1107646650L, 38986311L, 1147484014314L},
                {33511L, 1135050L, 1168561L, 1262045880L, 43463767L, 1365491346954L},
                {35720L, 1236664L, 1272384L, 1433976768L, 48343448L, 1618912700008L},
                {38024L, 1344952L, 1382976L, 1624996800L, 53651864L, 1912568964712L},
                {40425L, 1460200L, 1500625L, 1836765000L, 59416665L, 2251815973960L},
                {42925L, 1582700L, 1625625L, 2071046250L, 65666665L, 2642590973960L},
                {45526L, 1712750L, 1758276L, 2329715700L, 72431866L, 3091462060310L},
                {48230L, 1850654L, 1898884L, 2614763268L, 79743482L, 3605680701974L},
                {51039L, 1996722L, 2047761L, 2928298230L, 87633963L, 4193237479158L},
                {53955L, 2151270L, 2205225L, 3272553900L, 96137019L, 4862921163606L},
                {56980L, 2314620L, 2371600L, 3649892400L, 105287644L, 5624381272356L},
                {60116L, 2487100L, 2547216L, 4062809520L, 115122140L, 6488194228516L},
                {63365L, 2669044L, 2732409L, 4513939668L, 125678141L, 7465933265140L},
                {66729L, 2860792L, 2927521L, 5006060910L, 136994637L, 8570242210804L},
                {70210L, 3062690L, 3132900L, 5542100100L, 149111998L, 9814913298002L},
                {73810L, 3275090L, 3348900L, 6125138100L, 162071998L, 11214969138002L},
                {77531L, 3498350L, 3575881L, 6758415090L, 175917839L, 12786749008322L},
                {81375L, 3732834L, 3814209L, 7445335968L, 190694175L, 14547999601506L},
                {85344L, 3978912L, 4064256L, 8189475840L, 206447136L, 16517970386400L},
                {89440L, 4236960L, 4326400L, 8994585600L, 223224352L, 18717513735648L},
                {93665L, 4507360L, 4601025L, 9864597600L, 241074977L, 21169189975648L},
                {98021L, 4790500L, 4888521L, 10803631410L, 260049713L, 23897377517728L},
                {102510L, 5086774L, 5189284L, 11815999668L, 280200834L, 26928388231822L},
                {107134L, 5396582L, 5503716L, 12906214020L, 301582210L, 30290588226446L},
                {111895L, 5720330L, 5832225L, 14078991150L, 324249331L, 34014524201294L},
                {116795L, 6058430L, 6175225L, 15339258900L, 348259331L, 38133055541294L},
                {121836L, 6411300L, 6533136L, 16692162480L, 373671012L, 42681492323484L},
                {127020L, 6779364L, 6906384L, 18143070768L, 400544868L, 47697739410588L},
                {132349L, 7163052L, 7295401L, 19697582700L, 428943109L, 53222446807692L},
                {137825L, 7562800L, 7700625L, 21361533750L, 458929685L, 59299166460940L},
                {143450L, 7979050L, 8122500L, 23141002500L, 490570310L, 65974515679690L},
                {149226L, 8412250L, 8561476L, 25042317300L, 523932486L, 73298347366090L},
                {155155L, 8862854L, 9018009L, 27072063018L, 559085527L, 81323927238554L},
                {161239L, 9331322L, 9492561L, 29237087880L, 596100583L, 90108118238138L},
                {167480L, 9818120L, 9985600L, 31544510400L, 635050664L, 99711572309336L},
                {173880L, 10323720L, 10497600L, 34001726400L, 676010664L, 110198929749336L},
                {180441L, 10848600L, 11029041L, 36616416120L, 719057385L, 121639026322296L},
                {187165L, 11393244L, 11580409L, 39396551418L, 764269561L, 134105108337720L},
                {194054L, 11958142L, 12152196L, 42350403060L, 811727882L, 147675055894534L},
                {201110L, 12543790L, 12744900L, 45486548100L, 861515018L, 162431614494982L},
                {208335L, 13150690L, 13359025L, 48813877350L, 913715643L, 178462635234982L},
                {215731L, 13779350L, 13995081L, 52341602940L, 968416459L, 195861323780102L},
                {223300L, 14430284L, 14653584L, 56079265968L, 1025706220L, 214726498338836L},
                {231044L, 15104012L, 15335056L, 60036744240L, 1085675756L, 235162856847380L},
                {238965L, 15801060L, 16040025L, 64224260100L, 1148417997L, 257281253582628L},
                {247065L, 16521960L, 16769025L, 68652388350L, 1214027997L, 281198985422628L},
                {255346L, 17267250L, 17522596L, 73332064260L, 1282602958L, 307040087976258L},
                {263810L, 18037474L, 18301284L, 78274591668L, 1354242254L, 334935641806402L},
                {272459L, 18833182L, 19105641L, 83491651170L, 1429047455L, 365024088973426L},
                {281295L, 19654930L, 19936225L, 88995308400L, 1507122351L, 397451560128274L},
                {290320L, 20503280L, 20793600L, 94798022400L, 1588572976L, 432372212387024L},
                {299536L, 21378800L, 21678336L, 100912654080L, 1673507632L, 469948578221264L},
                {308945L, 22282064L, 22591009L, 107352474768L, 1762036913L, 510351925601168L},
                {318549L, 23213652L, 23532201L, 114131174850L, 1854273729L, 553762629630672L},
                {328350L, 24174150L, 24502500L, 121262872500L, 1950333330L, 600370555916670L},
                {338350L, 25164150L, 25502500L, 128762122500L, 2050333330L, 650375455916670L}};


        for(int i=0;i<6;i++){
            if(i!=5)System.out.print(mat[row][i]+" ");
            else{
                System.out.println(mat[row][i]);
            }
        }
    }
}