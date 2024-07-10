package com.shreyas.regularexpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("DanglingJavadoc")
public class RegXApi {
    public static void main(String[] args) {
        String sentence = "I like driving B.M.W. cars.";
        boolean matches = Pattern.matches("[A-Z].*[.]", sentence);
        System.out.println(matches);

        Pattern pattern = Pattern.compile("[A-Z].*?\\.");
        var matcher = pattern.matcher(sentence);

        System.out.println("Matches: " + matcher.matches());
        System.out.println("String length: " + sentence.length() + " " + "Matcher end: " + matcher.end() + " " + "Matched On: " + sentence.substring(0, matcher.end()));

        System.out.println("LookingAt: " + matcher.lookingAt());        // Returns: true if, and only if, a prefix of the input sequence matches this matcher's pattern
        System.out.println("String length: " + sentence.length() + " " + "Matcher end: " + matcher.end() + " " + "Matched On: " + sentence.substring(0, matcher.end()));

        System.out.println("Find: " + matcher.find());        // Returns: true if, and only if, a prefix of the input sequence matches this matcher's pattern
        System.out.println("String length: " + sentence.length() + " " + "Matcher end: " + matcher.end() + " " + "Matched On: " + sentence.substring(matcher.start(), matcher.end()));

        matcher.reset();
        System.out.println("Find: with reset now " + matcher.find());        // Returns: true if, and only if, a prefix of the input sequence matches this matcher's pattern
        System.out.println("String length: " + sentence.length() + " " + "Matcher end: " + matcher.end() + " " + "Matched On: " + sentence.substring(matcher.start(), matcher.end()));
        System.out.println("Group: " + matcher.group());

        System.out.println("-".repeat(20));


        String html = """
                <H1>My Heading</H1>
                <h2>Sub-Heading</h2>
                <p>My name is Shreyas</p>
                <h3>Summery</h3>
                """;
        Pattern pattern1 = Pattern.compile("<[hH]\\d>(.*)</[hH]\\d>");
        Matcher matcher1 = pattern1.matcher(html);
        while (matcher1.find()) {
            System.out.println("Group: " + matcher1.group() + " Group0: " + matcher1.group(0) +
                    " Group1: " + matcher1.group(1));
        }

        System.out.println("-".repeat(20));


        Pattern pattern2 = Pattern.compile("<[hH](?<level>\\d)>(.*)</[hH]\\d>");
        Matcher matcher2 = pattern2.matcher(html);
        while (matcher2.find()) {
            System.out.println("Group: " + matcher2.group() + " Group0: " + matcher2.group(0) +
                    " Group1: " + matcher2.group("level" /* 1 */) + " Group2: " + matcher2.group(2));
        }

        System.out.println("-".repeat(20));
        matcher2.reset();


        // MatchResult
        matcher2.results()
                .forEach(mr -> System.out.println(mr.group(1) + " " + mr.group(2)));

        System.out.println("-".repeat(20));
        matcher2.reset();


        String updatedSnippet = matcher2.replaceFirst("Replacement");   // Entire match is replaced
        System.out.println(updatedSnippet);

//        String updatedSnippet2 = matcher2.replaceFirst("<em> " + matcher2.group(2) + "</em>");

        /**
         * Above line throws error because I'm trying to use the capturing group, before any matches were actually done.
         * The replaceFirst method will perform a match, but in this case, I'm trying to access the
         * result before the method is even called. I could invoke find before this replaceFirst call,
         * but if I wanted to replace the next header, I'd have to keep doing this.
         * replaceFirst method calls reset matcher internally.
         */

//        System.out.println(updatedSnippet2);

        String updatedSnippet3 = matcher2.replaceFirst(matchResult ->
                "<em>" + matchResult.group(2) + "</em>");
        /**
         * There's an overloaded version of this method though, that takes a function,
         * and calls that function AFTER a match. This is the place to use your capturing group.
         * it takes one parameter, of type MatchResult, and needs to return a String. I can use the MatchResult to query the
         * capturing groups.
         */
        System.out.println(updatedSnippet3);

        /**
         * Using back references we can do the same thing as we did using Function in above example
         * in back reference start with $ and followed by capturing group name or group number
         */
        String updatedSnippet4 = matcher2.replaceFirst("<em>$2 is here</em>");
        System.out.println("Using back reference : " + updatedSnippet4);
        System.out.println("-".repeat(20));

        matcher2.usePattern(Pattern.compile("<([hH]\\d)>(.*)</\\1>"));
        /**
         * \\1 in above regX means back reference to 1st group
         */
        matcher2.reset();
        System.out.println(matcher2.replaceFirst("<em>$2</em>"));

        System.out.println("-".repeat(20));


        matcher2.reset();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (matcher2.find()) {
            matcher2.appendReplacement(sb,
                    switch (matcher2.group(1).toLowerCase()) {
                        case "h1" -> "<head>$2</head>";
                        case "h2" -> "<body>$2</body";
                        default -> "<$1>" + index++ + " $2<$1>";
                    });
        }
        matcher2.appendTail(sb);
        System.out.println(sb);

        var pattern3 = Pattern.compile("<[hH](?<level>\\d)>(.*)</[hH]\\d>" /* "<[hH](?<level>\\d)>(.*)(?i)</[hH]\\d>" */, Pattern.CASE_INSENSITIVE);
        /**
         * (?i) is not group it stands for case-insensitive.
         * we can also use Pattern.CASE_INSENSITIVE as flag while compiling pattern
         */
        var matcher3 = pattern3.matcher(html);


    }
}
