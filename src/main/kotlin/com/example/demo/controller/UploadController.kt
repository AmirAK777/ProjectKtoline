package com.example.demo.controller

import com.example.demo.domain.Client
import com.example.demo.repository.ClientRepository
import com.example.demo.service.ClientService
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


@Controller
@ControllerAdvice
class UploadController @Autowired constructor(private val clientRepository: ClientRepository, private val service: ClientService) {
    // un simple logger pour tracer des opérations ayant lieu dans cette classe
    // (basé sur la classe java qui sera construite à la compilation)
    var logger: Logger = LoggerFactory.getLogger(UploadController::class.java)

    @GetMapping("/upload")
    fun upload(model: Model): String {
        model["title"] = "Import de personnalités"
        return "import/upload"
    }

    @PostMapping("/upload") // //new annotation since 4.3
    fun import(
        @RequestParam("file") file: MultipartFile,
        redirectAttributes: RedirectAttributes
    ): String? {
        if (file.isEmpty) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload")
            return "redirect:import/uploadStatus"
        }

        try {
            val inputStream: InputStreamReader = InputStreamReader(file.inputStream,"UTF-8")
            val bufferedReader = BufferedReader(inputStream)
            val aFormat = CSVFormat.DEFAULT.builder()
                // choix des colonnes
                .setHeader("Number","Gender","NameSet","Title","GivenName","MiddleInitial","Surname","StreetAddress","City","State","StateFull","ZipCode","Country","CountryFull","EmailAddress","Username","Password","BrowserUserAgent","TelephoneNumber","TelephoneCountryCode","MothersMaiden","Birthday","TropicalZodiac","CCType","CCNumber","CVV2","CCExpires","NationalID","UPS","WesternUnionMTCN","MoneyGramMTCN","Color","Occupation","Company","Vehicle","Domain","BloodType","Pounds","Kilograms","FeetInches","Centimeters","Latitude","Longitude")
                .setIgnoreHeaderCase(true)
                .setSkipHeaderRecord(true)
                .setTrim(true)
                .build()
            val csvParser = CSVParser(bufferedReader, aFormat)

            var cpt: Int = 0
            var cptImportedClients: Int = 0

            for (csvRecord in csvParser) {
                val p: Client = Client(
                    title = csvRecord.get("Title"),
                    surname = csvRecord.get("Surname"),
                    givenName = csvRecord.get("GivenName"),
                    emailAddress = csvRecord.get("EmailAddress"),
                    birthday = csvRecord.get("Birthday"),
                    ccType = csvRecord.get("CCType"),
                    ccNumber = csvRecord.get("CCNumber"),
                    ccExpires = csvRecord.get("CCExpires"),
                    telephoneNumber = csvRecord.get("TelephoneNumber"),
                    streetAddress = csvRecord.get("StreetAddress"),
                    city = csvRecord.get("City"),
                    stateFull = csvRecord.get("StateFull"),
                    zipCode = csvRecord.get("ZipCode"),
                    centimeters = csvRecord.get("Centimeters"),
                    feetInches = csvRecord.get("FeetInches"),
                    latitude = csvRecord.get("Latitude"),
                    longitude = csvRecord.get("Longitude"),
                    vehicle = csvRecord.get("Vehicle"),
                    contrainte = ""


                )

                if (!clientRepository.existsClientBySurnameIgnoreCaseAndGivenNameIgnoreCase(p.surname, p.givenName)) {
                    val values = mutableListOf<String>()
                    if  (service.calculateAge(p.birthday) < 18 || service.calculateAge(p.birthday) > 80) {
                        values.add("Age")
                    }
                    if (service.tailleVerification(p.centimeters,p.feetInches)){
                        values.add("Taille")
                    }
                    if (clientRepository.existsClientByCcNumber(p.ccNumber)){
                        values.add("Doublons")
                    }
                    p.contrainte = values.joinToString(", ")
                    cptImportedClients++
                    clientRepository.save(p)
                }
                cpt++
                logger.info(p.toString())
                println("Message +$cpt")

            }

            redirectAttributes.addFlashAttribute(
                "message",
                "Successfully import '" + file.originalFilename + "' (" + cptImportedClients + " records)"
            )
        } catch (e: IOException) {
            e.printStackTrace()
            redirectAttributes.addFlashAttribute(
                "message",
                "Oups!"
            )
        } finally {
            //
        }
            return "redirect:/import/uploadStatus"
        }

    @ExceptionHandler(MaxUploadSizeExceededException::class)
    fun handleError(redirectAttributes: RedirectAttributes): String {
        logger.info("Catch MaxUploadSizeExceededException")
        redirectAttributes.addFlashAttribute("message", "Fichier trop gros !")
        return "redirect:/uploadStatus"
    }






        @GetMapping("/import/uploadStatus")
        fun uploadStatus(model: Model): String? {
            model["title"] = "Résultat de l'upload"
            return "import/uploadStatus"
        }

}