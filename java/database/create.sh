##!/bin/bash
#export PGPASSWORD='postgres1'
#BASEDIR=$(dirname $0)
#DATABASE=final_capstone
#psql -U postgres -f "$BASEDIR/dropdb.sql" &&
#createdb -U postgres $DATABASE &&
#psql -U postgres -d $DATABASE -f "$BASEDIR/schema.sql" &&
#psql -U postgres -d $DATABASE -f "$BASEDIR/data.sql" &&
#psql -U postgres -d $DATABASE -f "$BASEDIR/user.sql"

#!/bin/bash
export PGPASSWORD='postgres1'
BASEDIR=$(dirname "$0")
DATABASE=mbr_tracker

echo "Running dropdb.sql..."
psql -U postgres -f "$BASEDIR/dropdb.sql" || { echo 'Failed to run dropdb.sql'; exit 1; }

echo "Creating database $DATABASE..."
createdb -U postgres $DATABASE || { echo 'Failed to create database'; exit 1; }

echo "Running schema.sql..."
psql -U postgres -d $DATABASE -f "$BASEDIR/schema.sql" || { echo 'Failed to run schema.sql'; exit 1; }

echo "Running data.sql..."
psql -U postgres -d $DATABASE -f "$BASEDIR/data.sql" || { echo 'Failed to run data.sql'; exit 1; }

echo "Running user.sql..."
psql -U postgres -d $DATABASE -f "$BASEDIR/user.sql" || { echo 'Failed to run user.sql'; exit 1; }


echo "Script completed successfully."


