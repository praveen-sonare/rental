CREATE TABLE "rides" (
  "id" SERIAL PRIMARY KEY,
  "rider_id" int,
  "uuid" varchar(128),
  "ride_start_time" timestamp,
  "ride_end_time" timestamp,
  "start_odo" int,
  "end_odo" int,
  "vehicle_check_before_ride" int,
  "vehicle_check_after_ride" int,
  "status" int,
  "transaction_id" int NOT NULL,
  "txn_amount" varchar(10),
  "txn_comment" varchar(128),
  "txn_status" int
);

CREATE TABLE "vehicle" (
  "id" SERIAL PRIMARY KEY,
  "owner" int,
  "uuid" varchar(128) UNIQUE NOT NULL,
  "public_key" varchar(128) UNIQUE,
  "vehicle_name" varchar(512),
  "iot_device_id" varchar(512) NOT NULL,
  "iot_device_type" varchar(32) NOT NULL,
  "qa_details_id" int,
  "vehicle_type" varchar(20) NOT NULL,
  "vehicle_model" varchar(20) NOT NULL,
  "vehicle_color" varchar(20),
  "manufac_state_id" int,
  "manufac_state_user_id" int,
  "battery_cycle" int,
  "battery_serial_number" varchar(512),
  "battery_firmware_version" varchar(8),
  "controller_serial_number" varchar(512),
  "controller_software_version" varchar(8),
  "motor_serial" varchar(512),
  "particle_id" varchar(512),
  "particle_firmware_version" varchar(8),
  "odo" int,
  "height" int,
  "length" int,
  "width" int,
  "weight" int,
  "power" boolean NOT NULL,
  "locked" boolean NOT NULL,
  "is_available" boolean DEFAULT true,
  "status" boolean DEFAULT true,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "vehicle_check" (
  "id" int PRIMARY KEY,
  "vehicle_id" int,
  "headlight" smallint,
  "headlight_note" text,
  "mirror_light" smallint,
  "mirror_light_note" text,
  "mirror" smallint,
  "mirror_note" text,
  "turn_lights" smallint,
  "turn_lights_note" text,
  "handle_bar" smallint,
  "handle_bar_note" text,
  "cable" smallint,
  "cable_note" text,
  "screws" smallint,
  "screws_note" text,
  "fairings" smallint,
  "fairings_note" text,
  "tube" smallint,
  "tube_note" text,
  "created_at" timestamp,
  "updated_at" timestamp,
  "photo_location" varchar(256)
);

CREATE TABLE "tenant" (
  "id" SERIAL PRIMARY KEY,
  "username" varchar(32),
  "email" varchar(128),
  "profile_pic" text,
  "address_street" text,
  "country" varchar(20),
  "zip" varchar(16),
  "phone_number" varchar(20),
  "country_code" int,
  "phone_type" varchar,
  "created_at" timestamp,
  "updated_at" timestamp,
  "is_active" boolean DEFAULT true,
  "first_name" varchar(64),
  "last_name" varchar(64),
  "dob" date
);

CREATE TABLE "customer" (
  "id" SERIAL PRIMARY KEY,
  "username" varchar(32),
  "email" varchar(128),
  "profile_pic" text,
  "address_street" text,
  "country" varchar(20),
  "zip" varchar(16),
  "phone_number" varchar(20),
  "country_code" int,
  "phone_type" varchar,
  "created_at" timestamp,
  "updated_at" timestamp,
  "is_active" boolean DEFAULT true,
  "first_name" varchar(64),
  "last_name" varchar(64),
  "D_U_N_S" varchar(32),
  "tax_id" varchar(20),
  "entity_id" varchar(32)
);

CREATE TABLE "user" (
  "id" SERIAL PRIMARY KEY,
  "username" varchar(32),
  "email" varchar(128),
  "profile_pic" text,
  "address_street" text,
  "country" varchar(20),
  "zip" varchar(16),
  "phone_number" varchar(20),
  "country_code" int,
  "phone_type" varchar,
  "created_at" timestamp,
  "updated_at" timestamp,
  "is_active" boolean DEFAULT true,
  "first_name" varchar(64),
  "last_name" varchar(64),
  "dob" date,
  "gender" varchar(10),
  "user_id" varchar(20),
  "user_id_type" varchar(20),
  "about" text,
  "driving_license_no" varchar(20),
  "dl_issing_authority" varchar(20),
  "dl_issue_place" varchar(20),
  "dl_expiry" date,
  "employeeOf" int,
  "department" int,
  "clientOf" int
);

CREATE TABLE "business_plan" (
  "plan_id" int PRIMARY KEY,
  "customer_id" int NOT NULL,
  "vehicle_type" int NOT NULL,
  "need_picture" bool,
  "waiver" bool,
  "price" varchar(10),
  "duration" varchar(16) NOT NULL
);

COMMENT ON COLUMN "rides"."status" IS 'Initialize, Pre Verification, InProgress, Completed, Post Verification, Checkout';

COMMENT ON COLUMN "rides"."txn_comment" IS 'billing explanation';

COMMENT ON COLUMN "rides"."txn_status" IS 'Started, InProgress, Completed, Declined, Cancelled';

COMMENT ON COLUMN "vehicle"."manufac_state_user_id" IS 'Who is handling the manufac stage';

COMMENT ON COLUMN "vehicle"."power" IS 'Set to true if the vehicle is turned on. Otherwise false.';

COMMENT ON COLUMN "vehicle"."locked" IS 'Set to true if the vehicle is locked. Otherwise false.';

COMMENT ON COLUMN "vehicle"."is_available" IS 'true = available for Rent, false = uba';

COMMENT ON COLUMN "vehicle"."status" IS 'By default set to true. If deleted, set to false.';

COMMENT ON COLUMN "vehicle_check"."headlight" IS '1 for "ok", 2 for "flag", 3 for "fail"';

COMMENT ON COLUMN "tenant"."profile_pic" IS 'Profile pic is stored in a cloud storage and refernece will be stored here';

COMMENT ON COLUMN "tenant"."phone_number" IS 'Phone number with country code, starting with +';

COMMENT ON COLUMN "tenant"."is_active" IS 'By default set to true. If deleted, set to false.';

COMMENT ON COLUMN "customer"."profile_pic" IS 'Profile pic is stored in a cloud storage and refernece will be stored here';

COMMENT ON COLUMN "customer"."phone_number" IS 'Phone number with country code, starting with +';

COMMENT ON COLUMN "customer"."is_active" IS 'By default set to true. If deleted, set to false.';

COMMENT ON COLUMN "user"."profile_pic" IS 'Profile pic is stored in a cloud storage and refernece will be stored here';

COMMENT ON COLUMN "user"."phone_number" IS 'Phone number with country code, starting with +';

COMMENT ON COLUMN "user"."is_active" IS 'By default set to true. If deleted, set to false.';

COMMENT ON COLUMN "user"."user_id" IS 'voter id, SSN, FIN etc';

COMMENT ON COLUMN "user"."user_id_type" IS 'voter id, SSN, FIN etc';

COMMENT ON COLUMN "business_plan"."duration" IS '0-hourly, 1-daily, 2-monthly, 3-quarterly, 4-Anually, 5-Weekend, 6-Weekly';

ALTER TABLE "rides" ADD FOREIGN KEY ("rider_id") REFERENCES "user" ("id");

ALTER TABLE "rides" ADD FOREIGN KEY ("uuid") REFERENCES "vehicle" ("uuid");

ALTER TABLE "rides" ADD FOREIGN KEY ("vehicle_check_before_ride") REFERENCES "vehicle_check" ("id");

ALTER TABLE "rides" ADD FOREIGN KEY ("vehicle_check_after_ride") REFERENCES "vehicle_check" ("id");

ALTER TABLE "customer" ADD FOREIGN KEY ("id") REFERENCES "tenant" ("id");

ALTER TABLE "business_plan" ADD FOREIGN KEY ("customer_id") REFERENCES "customer" ("id");

ALTER TABLE "vehicle" ADD FOREIGN KEY ("owner") REFERENCES "customer" ("id");

ALTER TABLE "user" ADD FOREIGN KEY ("employeeOf") REFERENCES "customer" ("id");

ALTER TABLE "user" ADD FOREIGN KEY ("clientOf") REFERENCES "customer" ("id");

--https://dbdiagram.io/d/64ddd08b02bd1c4a5eec5c35