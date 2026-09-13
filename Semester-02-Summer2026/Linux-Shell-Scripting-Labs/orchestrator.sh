#!/bin/bash
# Enterprise Automation Orchestrator

LOG_FILE="./system_pipeline.log"
TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")

echo "[${TIMESTAMP}] 🚀 Initializing Data Pipeline Orchestration Layer..." >> "$LOG_FILE"

# Check internet connectivity to ensure API endpoint access
ping -c 1 google.com > /dev/null 2>&1
if [ $? -eq 0 ]; then
    echo "[${TIMESTAMP}] ✅ System Connectivity Verified. Launching Python ETL Core..." >> "$LOG_FILE"
    # Execute Python Extraction script (Creating the file next step)
    python3 ../Python-AI/etl_core.py >> "$LOG_FILE" 2>&1
else
    echo "[${TIMESTAMP}] ❌ System Connectivity Failed. Pipeline execution aborted." >> "$LOG_FILE"
    exit 1
fi
