package com.lbcinternal.sensemble.rest;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedXmlParser {
    // We don't use namespaces
    private static final String ns = null;

    public List<FeedEntry> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List<FeedEntry> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<FeedEntry> entries = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "ArrayOfNewsItem");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("NewsItem")) {
                entries.add(readCoupon(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
    // to their respective "read" methods for processing. Otherwise, skips the tag.
    private FeedEntry readCoupon(XmlPullParser parser) throws XmlPullParserException, IOException {
        String title = null;
        String body = null;
        Date creationDate = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tag = parser.getName();
            switch (tag) {
                case "Title":
                    title = readTitle(parser);
                    break;
                case "Story":
                    body = readBody(parser);
                    break;
                case "DatePublished":
                    creationDate = readDate(parser);
                    break;
            }
        }
        return new FeedEntry(title, body, creationDate);
    }

    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Title");
        return title;
    }

    private String readBody(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Story");
        String body = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Story");
        return body;
    }

    private Date readDate(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "DatePublished");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(readText(parser));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parser.require(XmlPullParser.END_TAG, ns, "DatePublished");
        return date;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}