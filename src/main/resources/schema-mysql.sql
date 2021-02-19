CREATE DATABASE IF NOT EXISTS narrativeio;
use narrativeio;

CREATE TABLE IF NOT EXISTS employee (
    id bigint NOT NULL, 
    userid VARCHAR(255), 
    event_type int,
    epoch_time VARCHAR(100),
    PRIMARY KEY (id)
);

DROP PROCEDURE IF EXISTS `getEventStatsByTimestamp`;

DELIMITER $$
CREATE PROCEDURE getEventStatsByTimestamp(in epochLowerBound bigint, in epochUpperBound bigint, out uniqueUsers int, out totalClicks int, out totalImpressions int)
BEGIN
with EventStats as (
select userid,
Count(case when event_type = 1 then 1 end) as Click,
Count(case when event_type = 0 then 1 end) as Impression
from event_records
where epoch_time BETWEEN epochLowerBound and epochUpperBound
group by userid
order by userid, Click, Impression
)
Select count(userid) as uniqueUser,sum(Click) as totalClick, sum(Impression) as totalImpression into uniqueUsers, totalClicks, totalImpressions
from EventStats;
END$$
DELIMITER ; 