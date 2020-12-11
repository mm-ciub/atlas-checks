package org.openstreetmap.atlas.checks.maproulette;

import org.openstreetmap.atlas.checks.event.CheckFlagEvent;
import org.openstreetmap.atlas.checks.flag.CheckFlag;
import org.openstreetmap.atlas.geography.atlas.Atlas;
import org.openstreetmap.atlas.geography.atlas.items.AtlasObject;
import org.openstreetmap.atlas.utilities.testing.CoreTestRule;
import org.openstreetmap.atlas.utilities.testing.TestAtlas;
import org.openstreetmap.atlas.utilities.testing.TestAtlas.Loc;
import org.openstreetmap.atlas.utilities.testing.TestAtlas.Point;

/**
 * Data for unit tests for MapRouletteUploadCommand
 *
 * @author nachtm
 * @author bbreithaupt
 */
public class MapRouletteUploadCommandTestRule extends CoreTestRule
{
    private static final String CENTER = "0,0";
    private static final String IDENTIFIER_1 = "1";
    private static final String IDENTIFIER_2 = "2";
    private static final String IDENTIFIER_3 = "3";
    private static final String IDENTIFIER_4 = "4";
    private static final String IDENTIFIER_5 = "5";
    private static final String CHALLENGE_1 = "SomeCheck";
    private static final String CHALLENGE_2 = "SomeOtherCheck";
    private static final String CHALLENGE_3 = "AnotherCheck";
    private static final String INSTRUCTIONS = "Instructions.";

    @TestAtlas(points = {
            @Point(coordinates = @Loc(value = CENTER), id = "1", tags = { "iso_country_code=USA" }),
            @Point(coordinates = @Loc(value = CENTER), id = "2", tags = { "iso_country_code=CAN" }),
            @Point(coordinates = @Loc(value = CENTER), id = "3", tags = {
                    "iso_country_code=MEX,BLZ" }),
            @Point(coordinates = @Loc(value = CENTER), id = "4", tags = {
                    "iso_country_code=URY" }) })
    private Atlas basicAtlas;

    public CheckFlagEvent getAnotherBasicFlag()
    {
        return this.getBasicFlag(IDENTIFIER_2, this.basicAtlas.point(2L), CHALLENGE_2);
    }

    public CheckFlagEvent getFlagNoObjects()
    {
        return this.getBasicFlag(IDENTIFIER_5, null, CHALLENGE_1);
    }

    public CheckFlagEvent getFlagSameCheck()
    {
        return this.getBasicFlag(IDENTIFIER_4, this.basicAtlas.point(4L), CHALLENGE_1);
    }

    public CheckFlagEvent getOneBasicFlag()
    {
        return this.getBasicFlag(IDENTIFIER_1, this.basicAtlas.point(1L), CHALLENGE_1);
    }

    public CheckFlagEvent getTwoCountryFlag()
    {
        return this.getBasicFlag(IDENTIFIER_3, this.basicAtlas.point(3L), CHALLENGE_3);
    }

    private CheckFlagEvent getBasicFlag(final String identifier, final AtlasObject object,
            final String challenge)
    {
        final CheckFlag flag = new CheckFlag(identifier);
        flag.addObject(object);
        flag.addInstruction(INSTRUCTIONS);

        return new CheckFlagEvent(challenge, flag);
    }
}
