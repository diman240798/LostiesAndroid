package nanicky.losties.android.core.extensions

import okio.Buffer
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.security.GeneralSecurityException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateFactory
import java.security.cert.CertificateParsingException
import java.security.cert.X509Certificate

fun String.md5(): String {
    try {
        val digest = MessageDigest.getInstance("MD5")
        digest.update(this.toByteArray())
        val messageDigest = digest.digest()

        // Create Hex String
        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2) h = "0$h"
            hexString.append(h)
        }

        return hexString.toString()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}

fun String.decodeCertificatePem(): X509Certificate {
    try {
        val certificateFactory = CertificateFactory.getInstance("X.509")
        val certificates = certificateFactory
            .generateCertificates(
                Buffer().writeUtf8(this).inputStream())

        return certificates.single() as X509Certificate
    } catch (nsee: NoSuchElementException) {
        throw CertificateParsingException(nsee)
    } catch (iae: IllegalArgumentException) {
        throw CertificateParsingException(iae)
    } catch (e: GeneralSecurityException) {
        throw IllegalArgumentException("failed to decode certificate", e)
    }
}

private const val CALENDAR_DATE_PATTERN = "yyyy-MM-dd"

private val jodaFormatter = DateTimeFormat.forPattern(CALENDAR_DATE_PATTERN)
private val threetenFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(CALENDAR_DATE_PATTERN)

fun String.toDateTime(): DateTime = jodaFormatter.parseDateTime(this)
fun String.toLocalDate(): LocalDate = LocalDate.parse(this, threetenFormatter)

fun String.removeZeroEnding(): String {
    if (this.endsWith(".0")) {
        return this.substring(0, this.length - 2)
    }
    return this
}