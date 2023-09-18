package just.fun;

import just.fun.defaults.fileio.FileDataSerializer;
import just.fun.defaults.fileio.FileDropper;
import just.fun.defaults.fileio.FileMetadataSerializer;
import just.fun.defaults.fileio.FileRowSerializer;
import just.fun.domain.schema.*;
import just.fun.domain.schema.types.Bool;
import just.fun.domain.schema.types.Numeric;
import just.fun.domain.schema.types.Textual;

import java.util.List;
import java.util.Map;

public class TestWithMain {

    private static final TestUtils TEST_UTILS;

    static {
        FileMetadataSerializer metadataSerializer = new FileMetadataSerializer();
        TEST_UTILS = new TestUtils(
                metadataSerializer, new FileDropper(), new FileRowSerializer(), new FileDataSerializer(metadataSerializer)
        );
    }

    public static void main(String[] args) {
        // profile table
        String profileTableName = "profile";
        Column<Object> idColumn = new Column<>("id", new Numeric());
        Column<Object> nameColumn = new Column<>("name", new Textual());
        Column<Object> ageColumn = new Column<>("age", new Numeric());
        Column<Object> isActiveColumn = new Column<>("is_active", new Bool());
        Columns profileColumns = new Columns(List.of(
                idColumn,
                nameColumn,
                ageColumn,
                isActiveColumn
        ));
        Metadata profileMetadata = new Metadata(profileTableName, profileColumns);

        // post table
        String postTableName = "post";
        Columns postColumns = new Columns(List.of(
                new Column<>("id", new Numeric()),
                new Column<>("date", new Textual()),
                new Column<>("content", new Textual()),
                new Column<>("views", new Numeric())
        ));
        Metadata postMetadata = new Metadata(postTableName, postColumns);

        // create table profile
        TEST_UTILS.create(profileTableName, profileMetadata);
        // create table post
        TEST_UTILS.create(postTableName, postMetadata);
        // drop table post (for now)
        TEST_UTILS.drop(postTableName);

        // rows to insert into profile
        Row row1 = Row.initRow(profileColumns, List.of("1", "poghos", "18", "TRUE"));
        Row row2 = Row.initRow(profileColumns, List.of("2", "petros", "19", "TRUE"));
        Row row3 = Row.initRow(profileColumns, List.of("3", "abraham", "20", "FALSE"));
        TEST_UTILS.insert(profileTableName, row1);
        TEST_UTILS.insert(profileTableName, row2);
        TEST_UTILS.insert(profileTableName, row3);

        // update row3
        Conditions updateConditions = new Conditions(List.of(new Condition<>(idColumn, 3)));
        Updates updates = new Updates(Map.of(isActiveColumn, true));
        TEST_UTILS.update(profileTableName, updateConditions, updates);

    }
}
