# Train Benchmark Controller scripts

### Overview

The controller is responsible for providing a configuration for Train Benchmark and also support management for building the actual projects, generating the models and also running the benchmark tests. Every process is executed on the base of a `config.json` file. To alter the default configuration of Train Benchmark, just modify `config.json` file. Find more information [here](https://github.com/FTSRG/trainbenchmark/wiki/Configuration).

### Requirements

* Git
* Maven 3
* Python 3 (tested with 3.4)

In the case of using virtual environment python3.4 interpreter is necessary.
Find further information about virtual environments [here](#install-virtual-environment-optional).

### Installation guide

It is necessary to install the required external modules for Python. Two options exist. The first one is installing packages globally and the second one is installing everything into a virtual environment.

#### Install globally

In this scenario every necessary external package will be installed globally to the system (e.g. pip, third-party modules). In this case just execute the following script from the `scripts/init/` directory:

```bash
./initialize.py
```

Note that root password is required for the successful deployment. As a result, the required third-party python modules will be installed and the Controller can be used already.

#### Install virtual environment (optional)

If deploying every external package globally on the system wouldn't be satisfying or feasible, then use a virtual environment. In this case run the following script from the `scripts/init/` directory

```bash
./initialize_venv.py
```

After this the python binary and third-party modules will be copied to the `./tb-env` folder which is also created automatically. Since the Python interpreter is not used globally, but locally, it must be activated every time:

```bash
source tb-env/bin/activate
```

After this operation the Controller can be already used.
To turn off the environment, execute the `deactivate` command. However, every time when a new terminal window is opened, the virtual environment must be activated again. That means it is a necessity to write the same activation command again to be able to use the environment.

### Usage

```bash
cd scripts/
```

Build the projects:

```bash
./run.py -b
```

Generate the models:

```bash
./run.py -g
```

The generated models will be created under the `models` directory in the root folder of the Train Benchmark.

Benchmarking:

```bash
./run.py -m
```

Build and generate the models instantly:

```bash
./run.py --generate
# or the shorter version
./run.py -g
 ```

It is also feasible to build only some parts of the Train Benchmark:

```bash
./run.py --tools
# or
./run.py --tools --format
```
For more information read the module's help page: `./run.py -h`

All in one:

```bash
./run.py --generate --benchmark
# or
./run.py -g -b
# or
./run.py -gb
```

### Configuration

Read the [wiki](https://github.com/FTSRG/trainbenchmark/wiki/Configuration).
