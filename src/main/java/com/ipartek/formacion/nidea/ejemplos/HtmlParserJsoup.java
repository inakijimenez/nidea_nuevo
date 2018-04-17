package com.ipartek.formacion.nidea.ejemplos;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParserJsoup {

	public static void main(String[] args) throws IOException {

		Document doc = Jsoup.connect("http://localhost:8080/nidea/login").method(Connection.Method.POST)
				.data("usuario", "admin").data("password", "admin").post();

		System.out.println(doc.getElementsByTag("h1").get(0));

		System.out.println(doc.getElementsByTag("div").get(0));

		Element nav = doc.getElementsByTag("div").get(0);
		Elements links = nav.getElementsByTag("a");

		Document materialesDoc;
		for (Element link : links) {
			String linkHref = link.attr("href");
			System.out.println(linkHref);
			if (linkHref.equals("backoffice/materiales")) {
				materialesDoc = Jsoup.connect(linkHref).get();
				System.out.println(materialesDoc);
			}
		}

		// Document doc = Jsoup.connect("http://example.com/").get();
		// String title = doc.title();
		//
		// System.out.println("Titulo " + title);
		//
		// Elements links = doc.getElementsByTag("a");
		//
		// for (Element link : links) {
		// String linkHref = link.attr("href");
		// Document doc2 = Jsoup.connect(linkHref).get();
		// String title2 = doc2.title();
		// System.out.println("Titulo2 " + title2);
		// }

	}

}
