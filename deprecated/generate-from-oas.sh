#!/bin/bash

# Configuration
OAS_FILE="webapp/src/main/resources/openapi/tasker-oas.yaml"
BASE_PACKAGE="com.example.tasker"
MODELS_OUTPUT="models/src/main/java"
SKELETON_OUTPUT="skeleton/src/main/java"
WEBAPP_OUTPUT="webapp/src/main/java"
SPRING_BOOT_VERSION="3.5.0"  # Match your Spring Boot version

# Clean previous generated code
echo "Cleaning previous generated code..."
rm -rf "${MODELS_OUTPUT}/$(echo ${BASE_PACKAGE} | tr '.' '/')/model"
rm -rf "${SKELETON_OUTPUT}/$(echo ${BASE_PACKAGE} | tr '.' '/')/api"
rm -rf "${WEBAPP_OUTPUT}/$(echo ${BASE_PACKAGE} | tr '.' '/')/controller"

# Generate Models (shared library)
echo "Generating models..."
openapi-generator-cli generate \
  -i "${OAS_FILE}" \
  -g java \
  -o /tmp/models \
  --model-package "${BASE_PACKAGE}.model" \
  --additional-properties="
    serializableModel=false,
    dateLibrary=java8,
    useJakartaEe=true,
    hideGenerationTimestamp=true"

# Remove all @Generated annotations from model classes
find "/tmp/models/src/main/java" -name "*.java" -exec sed -i \
  -e '/@javax.annotation.Generated/d' \
  -e '/@jakarta.annotation.Generated/d' \
  {} \;
# Move models to models module
echo "Moving models to models module..."
mkdir -p "${MODELS_OUTPUT}/$(echo ${BASE_PACKAGE} | tr '.' '/')/model"
mv /tmp/models/src/main/java/$(echo ${BASE_PACKAGE} | tr '.' '/')/model/* \
   "${MODELS_OUTPUT}/$(echo ${BASE_PACKAGE} | tr '.' '/')/model/"

# Generate API Interfaces (skeleton)
echo "Generating API interfaces..."
openapi-generator-cli generate \
  -i "${OAS_FILE}" \
  -g spring \
  -o /tmp/skeleton \
  --api-package "${BASE_PACKAGE}.api" \
  --model-package "${BASE_PACKAGE}.model" \
  --additional-properties="
    useSpringBoot=true,
    interfaceOnly=true,
    dateLibrary=java8,
    useJakartaEe=true,
    springBootVersion=${SPRING_BOOT_VERSION},
    hideGenerationTimestamp=true"

# Remove @Generated annotation and import from skeleton classes
find "/tmp/skeleton/src/main/java" -type f -name "*.java" -exec sed -i \
  -e '/import\s\+javax\.annotation\.Generated;/d' \
  -e '/import\s\+jakarta\.annotation\.Generated;/d' \
  -e '/@Generated\s*(/d' \
  {} \;

# Move API interfaces to skeleton module
echo "Moving API interfaces to skeleton module..."
mkdir -p "${SKELETON_OUTPUT}/$(echo ${BASE_PACKAGE} | tr '.' '/')/api"
mv /tmp/skeleton/src/main/java/$(echo ${BASE_PACKAGE} | tr '.' '/')/api/* \
   "${SKELETON_OUTPUT}/$(echo ${BASE_PACKAGE} | tr '.' '/')/api/"



# Generate Spring Boot Controllers (webapp)
echo "Generating Spring Boot controllers..."
openapi-generator-cli generate \
  -i "${OAS_FILE}" \
  -g spring \
  -o /tmp/webapp \
  --api-package "${BASE_PACKAGE}.controller" \
  --model-package "${BASE_PACKAGE}.model" \
  --additional-properties="
    useSpringBoot=true,
    reactive=false,
    delegatePattern=false,
    useBeanValidation=true,
    dateLibrary=java8,
    useJakartaEe=true,
    springBootVersion=${SPRING_BOOT_VERSION},
    unhandledException=false,
    performBeanValidation=true,
    java8=true,
    useTags=true
    hideGenerationTimestamp=true"

# Remove @Generated annotation and import from skeleton classes
find "/tmp/webapp/src/main/java" -type f -name "*.java" -exec sed -i \
  -e '/import\s\+javax\.annotation\.Generated;/d' \
  -e '/import\s\+jakarta\.annotation\.Generated;/d' \
  -e '/@Generated\s*(/d' \
  {} \;

# Move controllers to webapp module
echo "Moving controllers to webapp module..."
mkdir -p "${WEBAPP_OUTPUT}/$(echo ${BASE_PACKAGE} | tr '.' '/')/controller"
mv /tmp/webapp/src/main/java/$(echo ${BASE_PACKAGE} | tr '.' '/')/controller/* \
   "${WEBAPP_OUTPUT}/$(echo ${BASE_PACKAGE} | tr '.' '/')/controller/"

# Clean up
echo "Cleaning up temporary files..."
rm -rf /tmp/models /tmp/skeleton /tmp/webapp

echo "Spring Boot code generation complete!"