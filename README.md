# Universal-Robots-Gripper

## Anotace
Tato práce se zabývá procesem návrhu open source chapače pro kolaborativní robot UR5 a další
manipulátory společnosti Universal Robots. V návrhu je kladen důraz na snadnou integraci
chapače do robotického pracoviště, aby proces nebyl složitější než u komerčních modelů
chapačů a zároveň na minimální náročnost výrobního procesu. Popisuje návrh těla chapače,
vyráběného pomocí 3D tisku, návrh elektrického zapojení, použitých modulů a komponet pro
chapač a podrobně i vývoj softwaru sloužícího pro snadné použití chapače s robotem. Práce
obsahuje návod na výrobu, sestavení, připojení k robotu a uvedení chapače do provozu.
Přílohami této práce jsou podklady pro výrobu, tedy 3D modely všech dílů, schéma
elektrického zapojení, mechanický výkres, ovládací software a zkušební program. V závěru je
představen funkční produkt a popsáno zveřejnění výsledků práce veřejnosti ve formátu open
source projektu.
<b> Klíčová slova </b> <br>
Chapač, robot, návrh, řízení
## Annotation
This thesis focuses on the design process of an open-source gripper for the UR5 collaborative
robot and other manipulators from Universal Robots. The design emphasizes easy integration
of the gripper into the robotic workspace, ensuring that the process is no more complex than
with commercial gripper models while also minimizing manufacturing complexity. The thesis
describes the design of the gripper body, which is produced using 3D printing, the electrical
wiring, the modules and components used for the gripper, and provides a detailed overview of
the software development aimed at facilitating the gripper's use with the robot. The work
includes a guide for manufacturing, assembly, connection to the robot, and commissioning of
the gripper. The appendices contain manufacturing documentation, including 3D models of
all parts, an electrical wiring diagram, a mechanical drawing, control software, and a test
program. The conclusion presents the functional product and describes the public release of
the project results as an open-source project.
<b> Keywords </b> <br>
Gripper, robot, design, control

# DOKUMENTACE PRO KONSTRUKCI CHAPAČE
Tato část práce osahuje návod na sestavení, zprovoznění a základní způsob použití chapače s cobotem. Přílohou této práce je elektrické a mechanické schéma chapače. Mechanismus pohybu prstů chapače je převzatý z volně dostupné databáze 3D objektů Printables a jeho autorem je společnost A-11 Designs. Převzatý mechanismus jsem upravil v programu Fusion 360 za použití studentské licence a rozšířil ho o další části tak, aby splňoval nároky tohoto projektu. Rozšíření spočívalo ve vymodelování dílu pro uchycení krokového motoru a vnějšího krytu chránícího desku plošných spojů a vodiče. Vnější kryt zároveň umožnil spolehlivé upevnění chapače do příslušného rozhraní cobotu. Všechny zmíněné díly a součásti mechanismu prstů byly vyrobeny na 3D tiskárně z materiálu PET, ten jsem zvolil kvůli jeho lehkosti, pevnosti a dlouhodobé odolnosti. Výhodou této metody výroby dílů je možnost snadného a rychlého nahrazení poškozených nebo opotřebených součástí. Pro pevné spojení jednotlivých dílů jsou použity závitové vložky, zapuštěné přímo do dílů a šrouby. (13)
Proces konstrukce není časově náročný. Pokud pomineme čas, který vyžaduje 3D tisk součástek a vývoj desky plošných spojů, může být chapač sestaven, připojen k robotu a zprovozněn za 2 až 3 hodiny. O tom jsem se při sestavování prototypu sám přesvědčil. 
##	Výčet potřebných technologií a nástrojů
Jedním z cílů projektu je minimální náročnost konstrukce a maximální redukování potřebných technologií. Pro výrobu dílů těla chapače je použit 3D tisk, protože se v dnešní době čím dál více stává běžně dostupnou technologií. 3D tiskárnami je vybavena značná část středních odborných škol a gymnázií, ale i mnoho technologických firem a výrobních provozů. První prototyp byl vyroben na 3D tiskárně Prusa XL, ale malé rozměry dílů umožňují i využití tiskáren s menší pracovní plochou, jako jsou Prusa MK4 nebo Creality Ender-3. Za technologii výroby plošného spoje jsem zvolil ruční leptání desek s měděnou vrstvou. Tato jednoduchá technologie plně pokrývá nároky projektu a zbavuje potřeby využití služeb komerčního výrobce desek plošných spojů, což dále snižuje výsledné náklady na výrobu. Pro výrobu tak je potřeba pouze deska s vrstvou mědi a fotorezistu, maska pro leptání, která je přílohou práce, zdroj ultrafialového záření a vhodný leptací roztok, například chlorid železitý nebo persíran sodný. Pro osazení plošného spoje postačí vrtačka s vrtákem o průměru 0.5 mm, páječka a vhodná pájka, jelikož jsou použity výhradně THT  součástky. Dále už je potřeba pouze základní sada elektrikářských šroubováků a případně multimetr pro průběžnou kontrolu elektrického zapojení.
##	 Elektrické zapojení
Pro zprovoznění chapače je nejdříve potřeba správné zapojení elektronických součástek. K plošnému spoji, jehož výroba je popsána v předchozí kapitole, připájíme vodiče kabelu typu Lumberg RKMV 8-354 sloužícímu k napájení chapače a zároveň komunikaci s robotem podle schématu elektrického zapojení a této tabulky, která pro kontrolu obsahuje i umístění vodičů v konektoru. Schéma elektrického zapojení je přílohou této práce. (5)
<table>
Barva vodiče	Číslo pinu	Signál
Červená	8	0V(GND)
Šedá	5	0V/+12V/+24V (PWR)
Modrá	7	Digitální výstup 8 (DO8)
Růžová	6	Digitální výstup 9 (DO9)
Žlutá	4	Digitální vstup 8 (DI8)
Zelená	3	Digitální vstup 9 (DI9)
Bílá	1	Analogový vstup 2 (AI2)
Hnědá	2	Analogový vstup 3 (AI3)
</table>






Obrázek 12 - Schéma konektoru nástroje, převzato z https://community.particle.io/t/tracker-one-analog-sensor-reading-and-publishing/65011
K určeným pinům řadiče připojíme vinutí krokového motoru. Při zapojování dbáme, aby byly oba konce jednoho vinutí připojeny k sousedním pinům a nikoli střídavě. Je důležité nikdy nepřipojovat ani neodpojovat krokový motor, když je řadič pod napětím, jinak dojde k nevratnému poškození řadiče a bude potřeba ho vyměnit.
Pokud zamýšlíme používat chapač v dlouhých souvislých časových úsecích, je žádoucí přidat na čip řadiče pasivní chladič, protože při provozu delším, než několik minut vzroste teplota čipu a tím negativně ovlivní přesnost řízení motoru. K volným pinům DI8 a PWR připojíme kontakty koncového spínače umístěného na jedné z kontaktních plošek chapače, ten slouží pro detekci kontaktu s manipulovaným předmětem a pro kalibraci.

Obrázek 13 - Připojení krokového motoru, vytvořeno autorem
4.3	 Sestavení chapače
Pro sestavení chapače jsou potřeba součástky uvedené v následující tabulce v odpovídajícím množství. Přílohou této práce jsou modely všech dílů ve formátu STL, sloužící jako podklady pro 3D tisk. Vhodným materiálem pro výrobu je PLA nebo PETG. Použitému materiálu je potřeba přizpůsobit teplotu páječky při osazování závitových vložek a matice pro trapézovou tyč.













Tabulka 5 – Kusovník, vytvořeno autorem
Součástka	Množství, (popis)	Součástka	Množství, (popis)
 	1x
válcový kryt	 	1x
základní díl prstů
 	1x
zadní kryt	 	1x
držák krokového motoru
 	1x
středový díl	
 	4x
dlouhé táhlo
 	2x
prst chapače	 	4x
krátké táhlo
 	2x
středové táhlo	Krokový motor NEMA 17 17HS3401	1x
krokový motor
Šroub M5x15	8x	Šroub M4x20	4x
Šroub M8x10	4x	Šroub M5x10 samořezný	6x
Matice M5	8x	Matice M4	4x
Závitová vložka M5	8x	Trapézová závitová tyč M8	1x
Šroub M5x20	8x	Matice na závitovou tyč M8	1x
Šroub M3x5	4x	Hřídelová spojka M8	1x

Všechny obrázky použité v tabulce výše jsou autorské a byly pořízeny v programu Fusion 360 používaném ve studentské licenci.
Dle obrázku zapustíme do vyznačených otvorů vložky. Je potřeba vložky nahřát páječkou, zatlačit do otvoru a nechat vychladnout.










Dle obrázku přišroubujeme krokový motor čtyřmi šrouby M3x5 k držáku. Na osu krokového motoru připevníme hřídelovou spojku M8.

	
	









Dle obrázku přišroubujeme základní díl prstů čtyřmi šrouby M5x15.










K hřídelové spojce připevníme trapézovou tyč. Dle obrázku přišroubujeme válcový kryt čtyřmi šrouby M5x15. Ke krokovému motoru připojíme vodiče.



Dle obrázku sestavíme mechanismus prstů chapače. Do středového dílu po nahřátí páječkou vložíme matici M8. Pro červeně označené otvory použijeme 8 šroubů M5x20, pro modře označené otvory použijeme 4 šrouby M4x20 a odpovídající počet příslušných matic. 
Obrázek 18 - Sestavení, krok 5, foto: autor

K patici nástroje na cobotu přišroubujeme čtyřmi šrouby M8x10 zadní kryt chapače.
 
Obrázek 19 - Sestavení, krok 6, foto: autor

Připojíme plošný spoj ke krokovému motoru podle schématu elektrického zapojení a vložíme ho dovnitř chapače. Vyvedeme příslušným otvorem signální kabel z plošného spoje ven z těla chapače. Dle obrázku přišroubujeme zadní kryt chapače šesti šrouby M5x10. Tímto je chapač sestaven a připraven k použití.
 
Obrázek 20 - Sestavení, krok 7, foto: autor
Sestavený chapač je 235 mm dlouhý, tělo je v nejširším bodě široké 103 mm a maximální efektivní rozevření kontaktních ploch je 120 mm.
 
