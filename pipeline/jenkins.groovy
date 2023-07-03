pipeline {
    agent any
    parameters {
        choice(name: 'OS', choices: ['linux', 'windows', 'darwin'], description: 'Pick OS')
        choice(name: 'ARCH', choices: ['amd64', 'arm64'], description: 'Architecture to build for')
    }
    stages {
        stage('Build') {
            steps {
                echo "Building for platform: ${params.OS}"
                echo "Building for architecture: ${params.ARCH}"
                
                script {
                    if (params.OS == 'linux' && params.ARCH == 'amd64') {
                        sh 'make linux'
                    } else if (params.OS == 'darwin' && params.ARCH == 'amd64') {
                        sh 'make macOS'
                    }  else if (params.OS == 'linux' && params.ARCH == 'arm64') {
                        sh 'make arm'
                    }
                      else if (params.OS == 'windows' && params.ARCH == 'amd64') {
                        sh 'make windows'
                    
                }
            }
        }
    }
}
}
