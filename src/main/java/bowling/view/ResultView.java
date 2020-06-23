package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.dto.FrameResult;
import bowling.domain.frame.FrameNumber;
import bowling.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

    private static final String RESULT_FORMAT = "  %-4s";
    private static final String NAME_FORMAT = " %4s ";
    private static final String NAME = "NAME";
    private static final String FRAME_HEADER_NUMBER_FORMAT = "  0%s  ";
    private static final String DELIMITER = "|";

    private ResultView() {
    }

    public static void printHeader(final BowlingGame bowlingGame) {
        printShape(bowlingGame);
    }

    public static void printShape(final BowlingGame bowlingGame) {
        printFramesHeader();

        printNameInfo(bowlingGame.getPlayerName());
        printResult(bowlingGame.getFrameResults());
        System.out.println();
    }

    private static void printFramesHeader() {
        printNameInfo(NAME);
        printFrameNumber();
        System.out.println();
    }

    private static void printNameInfo(final String name) {
        System.out.print(DELIMITER + StringUtil.format(name, NAME_FORMAT) + DELIMITER);
    }

    private static void printFrameNumber() {
        IntStream.rangeClosed(FrameNumber.MIN_NUMBER, FrameNumber.MAX_NUMBER)
                .forEach(no -> System.out.print(
                        StringUtil.format(String.valueOf(no), FRAME_HEADER_NUMBER_FORMAT) + DELIMITER));
    }

    private static void printResult(final List<FrameResult> results) {
        if (Objects.isNull(results)) {
            printLine(Collections.emptyList());
            return;
        }

        printLine(results.stream()
                .map(FrameResult::getDesc)
                .collect(Collectors.toList())
        );
    }

    private static void printLine(final List<String> strings) {
        printFrame(strings);
        printFrame(new ArrayList<>(
                Collections.nCopies(FrameNumber.MAX_NUMBER - strings.size(), StringUtil.EMPTY)));
    }

    private static void printFrame(final List<String> strings) {
        strings.forEach(s -> System.out.print(StringUtil.format(s, RESULT_FORMAT) + DELIMITER));
    }
}
