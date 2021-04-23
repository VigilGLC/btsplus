const {execSync} = require('child_process');
const execPath = "../target/site/jacoco/jacoco.exec"
const classesPath = "../target/classes"
const xmlPath = "../target/site/jacoco/jacoco.xml"

execSync(
    'java -jar ./jacococli.jar ' +
    `report ${execPath} ` +
    `--classfiles ${classesPath} ` +
    `--xml ${xmlPath}`
);
process.exit(0)
