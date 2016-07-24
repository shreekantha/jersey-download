package com.spaneos.jerseypdf.endpoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Component
@Path("/download")
public class DownloadEndpoint {

	@Path("/pdf")
	@GET
	@Produces("application/vnd.pdf")
	public Response downloadPdf() throws IOException, DocumentException {

		File temp = File.createTempFile("sample", ".pdf");
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter.getInstance(document, new FileOutputStream(temp));
		// step 3
		document.open();
		// step 4
		document.add(new Paragraph("Hello World!"));
		// step 5
		document.close();

		return Response.ok((Object) temp).header("Content-Disposition", "attachment; filename=sample.pdf")
				.build();

	}

}
